package com.omb.boat.usecase;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.outbound.BoatPersistencePort;
import com.omb.boat.service.BoatDomainService;
import com.omb.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteBoatUseCaseTest {

    @Mock
    private BoatPersistencePort persistencePort;

    @InjectMocks
    private BoatDomainService domainService;

    @BeforeEach
    void setUp() {
        domainService = new BoatDomainService(persistencePort);
    }

    @Test
    @DisplayName("Test de la suppression d'un bateau")
    void given_existing_boat_then_delete_boat() {

        // Given
        UUID id = UUID.randomUUID();
        Boat boat = new Boat(id, "Titanic", Category.OCEAN_LINER, "Description", "REG-1", LocalDate.now());

        when(persistencePort.findById(id)).thenReturn(Optional.of(boat));
        doNothing().when(persistencePort).deleteById(id);

        // When
        domainService.deleteBoat(id);

        // Then
        verify(persistencePort).findById(id);
        verify(persistencePort).deleteById(id);
        verifyNoMoreInteractions(persistencePort);
    }

    @Test
    @DisplayName("Test de la levée d'une exception lors de la suppression d'un bateau existant")
    void given_nonexistent_boat_then_throw_exception() {

        // Given
        UUID id = UUID.randomUUID();
        when(persistencePort.findById(id)).thenReturn(Optional.empty());

        // When
        BusinessException exception = assertThrows(BusinessException.class,
                () -> domainService.deleteBoat(id)
        );

        // Then
        assertThat(exception.getMessage()).isEqualTo("Bateau non trouvé : " + id);

        verify(persistencePort).findById(id);
        verify(persistencePort, never()).deleteById(any());
        verifyNoMoreInteractions(persistencePort);
    }
}

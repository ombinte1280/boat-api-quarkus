package com.omb.boat.usecase;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.outbound.BoatPersistencePort;
import com.omb.boat.service.BoatDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveBoatUseCaseTest {

    @Mock
    private BoatPersistencePort persistencePort;

    @InjectMocks
    private BoatDomainService domainService;

    @BeforeEach
    void setUp() {
        domainService = new BoatDomainService(persistencePort);
    }

    @Test
    @DisplayName("Test du use case créer un nouveau bateau")
    void testSaveBoatUseCase() {
        // Given
        Boat boat = new Boat(UUID.randomUUID(), "Titanic", Category.OCEAN_LINER, "Description1"
                , "REG-1", LocalDate.now());
        when(persistencePort.save(boat)).thenReturn(boat);

        // When
        Boat newBoat = domainService.saveBoat(boat);

        // Then
        assertThat(newBoat).isNotNull();
        assertThat(newBoat.getId()).isEqualTo(boat.getId());
        assertThat(newBoat.getName()).isEqualTo(boat.getName());
        assertThat(newBoat.getCategory()).isEqualTo(boat.getCategory());
        assertThat(newBoat.getDescription()).isEqualTo(boat.getDescription());
        assertThat(newBoat.getRegistration()).isEqualTo(boat.getRegistration());
        assertThat(newBoat.getCreationDate()).isEqualTo(boat.getCreationDate());
        verifyNoMoreInteractions(persistencePort);
    }
}

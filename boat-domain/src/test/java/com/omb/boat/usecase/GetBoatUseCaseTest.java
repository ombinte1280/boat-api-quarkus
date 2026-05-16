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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBoatUseCaseTest {

    @Mock
    private BoatPersistencePort persistencePort;

    @InjectMocks
    private BoatDomainService domainService;

    @BeforeEach
    void setUp() {
        domainService = new BoatDomainService(persistencePort);
    }

    @Test
    @DisplayName("Test recupération d'un bateau par son ID")
    void given_existing_boat_then_return_boat() {
        // Given
        UUID id = UUID.randomUUID();
        Boat boat = new Boat(id, "Titanic", Category.OCEAN_LINER, "Description1"
                , "REG-1", LocalDate.now());
        when(persistencePort.findById(id)).thenReturn(Optional.of(boat));

        // When
        Boat expcetedBoat = domainService.getBoat(id);

        // Then
        assertThat(expcetedBoat).isNotNull();
        assertThat(expcetedBoat.getId()).isEqualTo(boat.getId());
        assertThat(expcetedBoat.getName()).isEqualTo(boat.getName());
        assertThat(expcetedBoat.getCategory()).isEqualTo(boat.getCategory());
        assertThat(expcetedBoat.getDescription()).isEqualTo(boat.getDescription());
        assertThat(expcetedBoat.getRegistration()).isEqualTo(boat.getRegistration());
        assertThat(expcetedBoat.getCreationDate()).isEqualTo(boat.getCreationDate());
        verifyNoMoreInteractions(persistencePort);
    }

    @Test
    @DisplayName("Test exception lors de la recuperation d'un bateau inexistant")
    void given_nonexistant_boat_then_throw_exception() {
        // Given
        UUID searchId = UUID.randomUUID();
        when(persistencePort.findById(searchId))
                .thenReturn(Optional.empty());

        // When
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            domainService.getBoat(searchId);
        });

        // Then
        assertThat(businessException.getMessage()).isEqualTo("Bateau non trouvé : " + searchId);
        verify(persistencePort).findById(searchId);
        verifyNoMoreInteractions(persistencePort);
    }


    @Test
    @DisplayName("Test recupération de tous les bateau")
    void should_return_all_boats() {
        // Given
        Boat boat1 = new Boat(UUID.randomUUID(), "Titanic", Category.OCEAN_LINER, "Description1"
                , "REG-1", LocalDate.now());
        Boat boat2 = new Boat(UUID.randomUUID(), "Titanic", Category.OCEAN_LINER, "Description1"
                , "REG-1", LocalDate.now());
        when(persistencePort.findAll()).thenReturn(List.of(boat1, boat2));

        // When
        List<Boat> expcetedList = domainService.getBoats();

        // Then
        assertThat(expcetedList).isNotNull();
        assertThat(expcetedList).hasSize(2);
        verifyNoMoreInteractions(persistencePort);
    }
}

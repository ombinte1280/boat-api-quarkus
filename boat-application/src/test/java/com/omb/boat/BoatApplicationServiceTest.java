package com.omb.boat;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.inbound.BoatApiPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoatApplicationServiceTest {

    @Mock
    private BoatApiPort boatApiPort;

    @InjectMocks
    private BoatApplicationService boatApplicationService;

    @BeforeEach
    void setUp() {
        boatApplicationService = new BoatApplicationService(boatApiPort);
    }

    @Test
    @DisplayName("Test de la création d'un bateau")
    void given_valid_data_should_create_boat() {

        // Given
        Boat savedBoat = new Boat(null, "Titanic", Category.YACHT
                , "Luxury boat", "REG-123", LocalDate.now());
        when(boatApiPort.saveBoat(any(Boat.class))).thenReturn(savedBoat);

        // When
        Boat result = boatApplicationService.createNewBoat("Titanic", "YACHT"
                        , "Luxury boat", "REG-123")
                .await().indefinitely();

        // then
        assertThat(result).isEqualTo(savedBoat);
        ArgumentCaptor<Boat> boatCaptor = ArgumentCaptor.forClass(Boat.class);

        verify(boatApiPort).saveBoat(boatCaptor.capture());

        Boat boatToSave = boatCaptor.getValue();

        assertThat(boatToSave.getId()).isNotNull();
        assertThat(boatToSave.getName()).isEqualTo("Titanic");
        assertThat(boatToSave.getCategory()).isEqualTo(Category.YACHT);
        assertThat(boatToSave.getDescription()).isEqualTo("Luxury boat");
        assertThat(boatToSave.getRegistration()).isEqualTo("REG-123");
        assertThat(boatToSave.getCreationDate()).isEqualTo(LocalDate.now());

        verifyNoMoreInteractions(boatApiPort);
    }

    @Test
    @DisplayName("Test de la recuperation de tous les bateau")
    void should_get_all_boats() {
        // Given
        List<Boat> boats = List.of(
                new Boat(UUID.randomUUID(), "Boat 1", Category.YACHT, "Description 1", "REG-1", LocalDate.now()),
                new Boat(UUID.randomUUID(), "Boat 2", Category.SAILBOAT, "Description 2", "REG-2", LocalDate.now())
        );

        when(boatApiPort.getBoats()).thenReturn(boats);

        // When
        List<Boat> result = boatApplicationService.getAllBoats()
                .await()
                .indefinitely();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(boats);

        verify(boatApiPort).getBoats();
        verifyNoMoreInteractions(boatApiPort);
    }

    @Test
    @DisplayName("Test de la récupération d'un bateau via son id")
    void given_existing_id_then_return_boat() {
        // Given
        UUID id = UUID.randomUUID();

        Boat boat = new Boat(id, "Titanic", Category.YACHT, "Luxury boat", "REG-123", LocalDate.now()
        );

        when(boatApiPort.getBoat(id)).thenReturn(boat);

        // When
        Boat result = boatApplicationService.getBoatById(id)
                .await()
                .indefinitely();

        // Then
        assertThat(result).isEqualTo(boat);

        verify(boatApiPort).getBoat(id);
        verifyNoMoreInteractions(boatApiPort);
    }

    @Test
    @DisplayName("Test de la mise à jour d'un bateau")
    void given_existing_boat_then_update_boat() {
        // Given
        UUID id = UUID.randomUUID();

        Boat existingBoat = new Boat(id, "Old name", Category.SAILBOAT, "Old description"
                , "OLD-REG", LocalDate.now());

        Boat savedBoat = new Boat(id, "New name", Category.YACHT, "New description"
                , "NEW-REG", existingBoat.getCreationDate());

        when(boatApiPort.getBoat(id)).thenReturn(existingBoat);
        when(boatApiPort.saveBoat(existingBoat)).thenReturn(savedBoat);

        // When
        Boat result = boatApplicationService.updateBoat(id, "New name", Category.YACHT, "New description", "NEW-REG"
        ).await().indefinitely();

        // Then
        assertThat(result).isEqualTo(savedBoat);

        assertThat(existingBoat.getName()).isEqualTo("New name");
        assertThat(existingBoat.getCategory()).isEqualTo(Category.YACHT);
        assertThat(existingBoat.getDescription()).isEqualTo("New description");
        assertThat(existingBoat.getRegistration()).isEqualTo("NEW-REG");

        verify(boatApiPort).getBoat(id);
        verify(boatApiPort).saveBoat(existingBoat);
        verifyNoMoreInteractions(boatApiPort);
    }

    @Test
    @DisplayName("Test de la mise que des champs non nul")
    void should_update_only_non_null_fields() {

        // Given
        UUID id = UUID.randomUUID();

        Boat existingBoat = new Boat(id, "Old name", Category.SAILBOAT, "Old description"
                , "OLD-REG", LocalDate.now());

        when(boatApiPort.getBoat(id)).thenReturn(existingBoat);
        when(boatApiPort.saveBoat(existingBoat)).thenReturn(existingBoat);

        // When
        Boat result = boatApplicationService.updateBoat(id, "New name", null, null, null
        ).await().indefinitely();

        // Then
        assertThat(result).isEqualTo(existingBoat);

        assertThat(existingBoat.getName()).isEqualTo("New name");
        assertThat(existingBoat.getCategory()).isEqualTo(Category.SAILBOAT);
        assertThat(existingBoat.getDescription()).isEqualTo("Old description");
        assertThat(existingBoat.getRegistration()).isEqualTo("OLD-REG");

        verify(boatApiPort).getBoat(id);
        verify(boatApiPort).saveBoat(existingBoat);
        verifyNoMoreInteractions(boatApiPort);

    }

    @Test
    @DisplayName("Test de la suppression d'un bateau")
    void given_valid_existing_id_then_delete_boat() {
        // Given
        UUID id = UUID.randomUUID();

        doNothing().when(boatApiPort).deleteBoat(id);

        // When
        Void result = boatApplicationService.deleteBoat(id)
                .await()
                .indefinitely();

        // Then
        assertThat(result).isNull();

        verify(boatApiPort).deleteBoat(id);
        verifyNoMoreInteractions(boatApiPort);
    }

}

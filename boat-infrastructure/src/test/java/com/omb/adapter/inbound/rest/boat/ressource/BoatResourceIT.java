package com.omb.adapter.inbound.rest.boat.ressource;

import com.omb.boat.BoatApplicationService;
import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
public class BoatResourceIT {

    @InjectMock
    BoatApplicationService boatApplication;

    @Test
    void should_create_boat() {

        Boat boat = new Boat(UUID.randomUUID(), "Titanic", Category.YACHT
                , "Luxury boat", "REG-123", LocalDate.now());

        when(boatApplication.createNewBoat(
                anyString(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(boat);

        given()
                .contentType("application/json")
                .body("""
                        {
                          "name": "Titanic",
                          "category": "YACHT",
                          "description": "Luxury boat",
                          "registration": "REG-123"
                        }
                        """)
                .when()
                .post("/boats")
                .then()
                .statusCode(201)
                .body("name", equalTo("Titanic"))
                .body("category", equalTo("YACHT"))
                .body("registration", equalTo("REG-123"));

    }

    @Test
    void should_get_boat_by_id() {

        UUID id = UUID.randomUUID();
        Boat boat = new Boat(
                id,
                "Titanic",
                Category.YACHT,
                "Luxury boat",
                "REG-123",
                LocalDate.now()
        );

        when(boatApplication.getBoatById(id))
                .thenReturn(boat);

        given()
                .when()
                .get("/boats/" + id)
                .then()
                .statusCode(200)
                .body("name", equalTo("Titanic"));
    }

    @Test
    void should_update_boat() {

        UUID id = UUID.randomUUID();
        Boat updatedBoat = new Boat(id, "Updated Titanic", Category.YACHT, "Updated description"
                , "REG-999", LocalDate.now());

        when(boatApplication.updateBoat(
                eq(id),
                eq("Updated Titanic"),
                eq(Category.YACHT),
                eq("Updated description"),
                eq("REG-999")
        )).thenReturn(updatedBoat);

        given()
                .contentType("application/json")
                .body("""
                    {
                      "name": "Updated Titanic",
                      "category": "YACHT",
                      "description": "Updated description",
                      "registration": "REG-999"
                    }
                    """)
                .when()
                .patch("/boats/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.toString()))
                .body("name", equalTo("Updated Titanic"))
                .body("category", equalTo("YACHT"))
                .body("description", equalTo("Updated description"))
                .body("registration", equalTo("REG-999"));

        verify(boatApplication).updateBoat(
                eq(id),
                eq("Updated Titanic"),
                eq(Category.YACHT),
                eq("Updated description"),
                eq("REG-999")
        );
    }

    @Test
    void should_delete_boat() {

        UUID id = UUID.randomUUID();
        doNothing().when(boatApplication).deleteBoat(id);

        given()
                .when()
                .delete("/boats/" + id)
                .then()
                .statusCode(204);

        verify(boatApplication).deleteBoat(id);
    }
}

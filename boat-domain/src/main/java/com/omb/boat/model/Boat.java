package com.omb.boat.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Boat {
    private final UUID id;
    private String name;
    private Category category;
    private String description;
    private String registration;
    private final LocalDate creationDate;

    public Boat(UUID id, String name, Category category, String description, String registration, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.registration = registration;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return Objects.equals(id, boat.id) && Objects.equals(name, boat.name) && category == boat.category
                && Objects.equals(description, boat.description) && Objects.equals(registration, boat.registration)
                && Objects.equals(creationDate, boat.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, registration, creationDate);
    }
}


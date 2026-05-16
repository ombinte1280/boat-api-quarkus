package com.omb.adapter.outbound.persistence.entity;

import com.omb.boat.model.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "boats")
@Getter
@Setter
@NoArgsConstructor
public class BoatEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    private String description;
    @Column(nullable = false)
    private String registration;
    @NotNull
    @Column(nullable = false)
    private LocalDate creationDate;
}

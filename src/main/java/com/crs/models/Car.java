package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    private String bodyType;
    private String year;
    private String color;
    private int mileage;
    @Enumerated(value = EnumType.STRING)
    private CarStatus status;
    private double pricePerDay;
    private boolean isActive;
    private String imageUrl;
}

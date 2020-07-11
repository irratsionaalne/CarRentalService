package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue
    private UUID id;
    private String make;
    private String model;
    private String bodyType;
    private String year;
    private String color;
    private int mileage;
    @Enumerated(value = EnumType.STRING)
    private CarStatus status;
    private double pricePerDay;
    private boolean isActive;
}

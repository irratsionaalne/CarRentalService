package com.crs.controllers.dto;

import com.crs.models.CarStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class CarDto {

    @NotEmpty
    private String make;

    @NotEmpty
    private String model;

    @NotEmpty
    private String bodyType;

    @NotEmpty
    private String year;

    @NotEmpty
    private String color;

    @NotEmpty
    private int mileage;

    @NotEmpty
    private CarStatus status;

    @NotEmpty
    private double pricePerDay;

}

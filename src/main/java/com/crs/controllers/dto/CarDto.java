package com.crs.controllers.dto;

import com.crs.models.CarStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class CarDto {

    private Long id;

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
    @Min(0)
    private int mileage;

    @NotEmpty
    private CarStatus status;

    @NotEmpty
    private double pricePerDay;

}

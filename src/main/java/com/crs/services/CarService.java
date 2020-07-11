package com.crs.services;

import com.crs.dto.CarDto;
import com.crs.models.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {

    Car createCar(CarDto carDto) throws Exception;

    boolean updateCar(Car car) throws Exception;

    List<Car> getAllCars();

    Car getById(UUID carId);

}

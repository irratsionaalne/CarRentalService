package com.crs.services;

import com.crs.models.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {

    boolean createCar(Car car) throws Exception;

    boolean updateCar(Car car) throws Exception;

    List<Car> getAllCars();

    Car getById(UUID carId);

}

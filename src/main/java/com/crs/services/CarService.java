package com.crs.services;

import com.crs.models.Car;

import java.util.List;

public interface CarService {

    boolean createCar(Car car) throws Exception;

    boolean updateCar(Car car) throws Exception;

    boolean setCarStatus(Long carId) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

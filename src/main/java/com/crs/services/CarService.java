package com.crs.services;

import com.crs.controllers.dto.CarDto;
import com.crs.models.Car;

import java.util.List;

public interface CarService {

    Car createCar(CarDto carDto) throws Exception;

    boolean updateCar(Car car) throws Exception;

    boolean setCarStatus(Long carId) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

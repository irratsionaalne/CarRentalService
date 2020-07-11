package com.crs.services;

import com.crs.dto.CarDto;
import com.crs.models.Car;

import java.util.List;

public interface CarService {

    Car createCar(CarDto carDto) throws Exception;

    boolean updateCar(Car car) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

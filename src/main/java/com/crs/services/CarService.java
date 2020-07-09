package com.crs.services;

import com.crs.controllers.dto.CarDto;
import com.crs.models.Car;

import java.util.List;

public interface CarService {

    Car createCar(CarDto carDto) throws Exception;

    Car convertToCar(CarDto carDto);

    CarDto convertToCarDto(Car car);

    boolean updateCar(Car car) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

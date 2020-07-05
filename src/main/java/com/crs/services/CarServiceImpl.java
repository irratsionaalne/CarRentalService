package com.crs.services;

import com.crs.controllers.dto.CarDto;
import com.crs.models.Car;
import com.crs.models.CarStatus;
import com.crs.repositories.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepo carRepo;

    @Override
    public Car createCar(CarDto carDto) throws Exception {
        Car car = new Car();
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setBodyType(carDto.getBodyType());
        car.setYear(carDto.getYear());
        car.setColor(carDto.getColor());
        car.setMileage(carDto.getMileage());
        car.setStatus(carDto.getStatus());
        car.setPricePerDay(carDto.getPricePerDay());
        car.setActive(true);

        return carRepo.save(car);
    }

    @Override
    public boolean updateCar(Car car) throws Exception {
        if (car == null || !carRepo.existsById(car.getId())) {
            throw new Exception("Invalid updating of Car");
        }
        carRepo.saveAndFlush(car);
        return true;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    @Override
    public Car getById(Long carId) {
        return carRepo.getOne(carId);
    }
}

package com.crs.services;

import com.crs.dto.CarDto;
import com.crs.models.Car;
import com.crs.repositories.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Car getById(UUID carId) {
        return carRepo.getOne(carId);
    }
}

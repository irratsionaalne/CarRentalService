package com.crs.services;


import com.crs.models.Car;
import com.crs.repositories.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepo;

    @Override
    public boolean createCar(Car car) throws Exception {
        car.setActive(true);
        carRepo.save(car);
        return true;
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

package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Car;
import CarRentalService.CRS.Repositories.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    public CarRepo carRepo;


    @Override
    public boolean createCar(Car car) {
        if (car == null) {
            return false;
        }
        car.setActive(true);
        carRepo.save(car);
        return true;
    }

    @Override
    public boolean updateCar(Car car) {
        if (car == null || !carRepo.existsById(car.getId())) {
            return false;
        }
        carRepo.saveAndFlush(car);
        return true;
    }

    @Override
    public boolean deleteCar(Long carId) {
        Car car = getById(carId);
        if (car == null) {
            return false;
        }
        car.setActive(false);
        updateCar(car);
        return true;
    }

    @Override
    public boolean restoreCar(Long carId) {
        Car car = getById(carId);
        if (car == null) {
            return false;
        }
        car.setActive(true);
        restoreCar(carId);
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



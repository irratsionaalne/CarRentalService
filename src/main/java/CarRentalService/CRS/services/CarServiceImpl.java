package CarRentalService.crs.services;

import CarRentalService.crs.models.Car;
import CarRentalService.crs.repositories.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    public CarRepo carRepo;


    @Override
    public boolean createCar(Car car) throws Exception {
        if (car == null) {
            throw new Exception("Invalid update for Car");
        }
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
    public boolean deleteCar(Long carId) throws Exception {
        Car car = getById(carId);
        if (car == null) {
            throw new Exception("Invalid deleting of Car");
        }
        car.setActive(false);
        deleteCar(carId);
        return true;
    }

    @Override
    public boolean restoreCar(Long carId) throws Exception {
        Car car = getById(carId);
        if (car == null) {
            throw new Exception("Invalid restoration of  Customer");
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



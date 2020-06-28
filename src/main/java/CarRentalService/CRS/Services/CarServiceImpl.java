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
    public boolean setCarStatus(Long carId) throws Exception {

        Car car = getById(carId);
        if (car == null) {
            throw new Exception("Invalid deleting of Car");
        }
        if(car.isActive()) {
            car.setActive(false);
        }
        car.setActive(true);
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

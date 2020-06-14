package CarRentalService.crs.services;

import CarRentalService.crs.models.Car;

import java.util.List;

public interface CarService {

    boolean createCar(Car car) throws Exception;

    boolean updateCar(Car car) throws Exception;

    boolean deleteCar(Long carId) throws Exception;

    boolean restoreCar(Long carId) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

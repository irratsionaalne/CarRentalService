package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Car;

import java.util.List;

public interface CarService {

    boolean createCar(Car car);

    boolean updateCar(Car car);

    boolean deleteCar(Long carId);

    boolean restoreCar(Long carId);

    List<Car> getAllCars();

    Car getById(Long carId);

}

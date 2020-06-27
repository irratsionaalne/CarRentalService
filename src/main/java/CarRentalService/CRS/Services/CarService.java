package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Car;

import java.util.List;

public interface CarService {

    boolean createCar(Car car) throws Exception;

    boolean updateCar(Car car) throws Exception;

    boolean deleteCar(Long carId) throws Exception;

    boolean restoreCar(Long carId) throws Exception;

    List<Car> getAllCars();

    Car getById(Long carId);

}

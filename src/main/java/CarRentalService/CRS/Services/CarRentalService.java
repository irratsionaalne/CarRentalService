package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.CarRental;

import java.util.List;

public interface CarRentalService {

    boolean createCarRental(CarRental carRental);

    boolean updateCarRental(CarRental carRental);

    boolean deleteCarRental(Long carRentalId);

    boolean restoreCarRental(Long carRentalId);

    CarRental getById(Long carRentalId);

    boolean addComment();
}

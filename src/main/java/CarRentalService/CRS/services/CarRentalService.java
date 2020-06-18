package CarRentalService.crs.services;

import CarRentalService.crs.models.CarRental;

public interface CarRentalService {

    boolean createCarRental(CarRental carRental);

    boolean updateCarRental(CarRental carRental);

    boolean deleteCarRental(Long carRentalId);

    boolean restoreCarRental(Long carRentalId);

    CarRental getById(Long carRentalId);

    boolean addComment();
}

package CarRentalService.CRS.services;

import CarRentalService.CRS.models.CarReturn;

public interface CarReturnService {

    boolean createCarReturn(CarReturn carReturn);

    boolean updateCarReturn(CarReturn carReturn);

    boolean deleteCarReturn(Long carReturnId);

    boolean restoreCarReturn(Long carReturnId);

    CarReturn getById(Long carReturnId);

    boolean addComment();

    boolean addAdditionalPayment();
}

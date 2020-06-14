package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.CarReturn;

import java.util.List;

public interface CarReturnService {

    boolean createCarReturn(CarReturn carReturn);

    boolean updateCarReturn(CarReturn carReturn);

    boolean deleteCarReturn(Long carReturnId);

    boolean restoreCarReturn(Long carReturnId);

    CarReturn getById(Long carReturnId);

    boolean addComment();

    boolean addAdditionalPayment();
}

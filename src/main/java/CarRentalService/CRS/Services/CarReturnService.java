package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.CarRental;
import CarRentalService.CRS.Models.CarReturn;

import java.util.List;

public interface CarReturnService {

    boolean createCarReturn(CarReturn carReturn) throws Exception;

    boolean updateCarReturn(CarReturn carReturn) throws Exception;

    boolean deleteCarReturn(Long carReturnId) throws Exception;

    boolean restoreCarReturn(Long carReturnId) throws Exception;

    CarReturn getById(Long carReturnId);

    boolean addComment(Long carReturnId,String carRentalComment);

    boolean addAdditionalPayment(Long carReturnId,String carAdditionalPayment);

    List<CarReturn> getAllCarReturns();
}

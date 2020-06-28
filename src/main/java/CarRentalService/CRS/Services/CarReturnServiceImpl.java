package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.CarRental;
import CarRentalService.CRS.Models.CarReturn;
import CarRentalService.CRS.Repositories.CarReturnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarReturnServiceImpl implements CarReturnService {

    @Autowired
    private CarReturnRepo carReturnRepo;

    @Override
    public boolean createCarReturn(CarReturn carReturn) throws Exception {
        if (carReturn == null) {
            throw new Exception("Invalid input ... ");
        }
        carReturnRepo.save(carReturn);
        return true;
    }

    @Override
    public boolean updateCarReturn(CarReturn carReturn) throws Exception {
        if (carReturn == null || carReturnRepo.existsById(carReturn.getId())) {
            throw new Exception("Invalid input ... ");
        }
        carReturnRepo.saveAndFlush(carReturn);
        return true;
    }

    @Override
    public boolean setCarReturnStatus(Long carReturnId) throws Exception {
        CarReturn carReturn = getById(carReturnId);
        if (carReturnId == null) {
            throw new Exception("Invalid input ... ");
        }
        if(carReturn.isActive()) {
            carReturn.setActive(false);
        }
        carReturn.setActive(true);
        updateCarReturn(carReturn);
        return true;
    }

    @Override
    public CarReturn getById(Long carReturnId) {
        return carReturnRepo.getOne(carReturnId);
    }

    @Override
    public boolean addComment(Long carReturnId,String carRentalComment) {
        CarReturn carReturn = carReturnRepo.getOne(carReturnId);
        carReturn.setComment(carRentalComment);
        carReturnRepo.save(carReturn);
        return true;
    }

    @Override
    public boolean addAdditionalPayment(Long carReturnId,String carAdditionalPayment) {
        CarReturn carReturn = carReturnRepo.getOne(carReturnId);
        carReturn.setAdditionalPayment(carAdditionalPayment);
        carReturnRepo.save(carReturn);
        return true;
    }
    @Override
    public List<CarReturn> getAllCarReturns() {
        return carReturnRepo.findAll();
    }
}

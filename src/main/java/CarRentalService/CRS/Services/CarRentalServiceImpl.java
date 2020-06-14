package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.CarRental;
import CarRentalService.CRS.Repositories.CarRentalRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CarRentalServiceImpl implements CarRentalService {
    
    @Autowired
    private CarRentalRepo carRentalRepo;
    
    @Override
    public boolean createCarRental(CarRental carRental) throws Exception {
        if (carRental == null) {
            throw new Exception("Invalid input ... ");
        }
        carRentalRepo.save(carRental);
        return true;
    }

    @Override
    public boolean updateCarRental(CarRental carRental) throws Exception {
        if (carRental == null || carRentalRepo.existsById(carRental.getId())) {
            throw new Exception("Invalid input ... ");
        }
        carRentalRepo.saveAndFlush(carRental);
        return true;
    }

    @Override
    public boolean deleteCarRental(Long carRentalId) throws Exception {
        CarRental carRental = getById(carRentalId);
        if (carRentalId == null) {
            throw new Exception("Invalid input ... ");
        }
        carRental.setActive(false);
        updateCarRental(carRental);
        return true;
    }

    @Override
    public boolean restoreCarRental(Long carRentalId) throws Exception {

        CarRental carRental= getById(carRentalId);
        if (carRental == null) {
            throw new Exception("Invalid input ... ");
        }

        carRental.setActive(true);
        return updateCarRental(carRental);
    }

    @Override
    public CarRental getById(Long carRentalId) {
        return carRentalRepo.getOne(carRentalId);
    }

    @Override
        public boolean addComment(Long carRentalId,String carRentalComment) {
            CarRental carRental = carRentalRepo.getOne(carRentalId);
            carRental.setComment(carRentalComment);
            carRentalRepo.save(carRental);
            return true;
    }
}

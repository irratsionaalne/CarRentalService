package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Office;
import CarRentalService.CRS.Repositories.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    @Override
    public boolean createOffice(Office office) {
        if (office == null) {
            return false;
        }

        office.setActive(true);
        officeRepo.save(office);
        return true;
    }

    @Override
    public boolean updateOffice(Office office) {
        if (office == null || !officeRepo.existsById(office.getId())) {
            return false;
        }

        officeRepo.saveAndFlush(office);
        return true;
    }

}

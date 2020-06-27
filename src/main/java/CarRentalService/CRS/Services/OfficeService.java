package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Office;

import java.util.List;

public interface OfficeService {

    boolean createOffice(Office office) throws Exception;

    boolean updateOffice(Office office);

    List<Office> getAllOffices();

    Office getById(Long officeId);

}

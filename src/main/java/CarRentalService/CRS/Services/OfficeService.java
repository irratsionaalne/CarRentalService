package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Office;

public interface OfficeService {

    boolean createOffice(Office office) throws Exception;

    boolean updateOffice(Office office);

}

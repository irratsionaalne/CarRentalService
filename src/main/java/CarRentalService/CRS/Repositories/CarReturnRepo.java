package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.CarReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarReturnRepo extends JpaRepository<CarReturn, Long> {
}

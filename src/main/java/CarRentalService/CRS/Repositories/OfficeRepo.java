package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Long> {
}

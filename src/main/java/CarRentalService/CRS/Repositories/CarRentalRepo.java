package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepo extends JpaRepository<CarRental, Long> {
}

package CarRentalService.crs.repositories;

import CarRentalService.crs.models.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepo extends JpaRepository<CarRental, Long> {
}

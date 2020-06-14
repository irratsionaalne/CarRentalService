package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}

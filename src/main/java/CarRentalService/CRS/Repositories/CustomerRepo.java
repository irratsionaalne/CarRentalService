package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}

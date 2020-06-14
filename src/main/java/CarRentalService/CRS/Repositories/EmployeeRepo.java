package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}

package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepo extends JpaRepository<Branch, Long> {
}

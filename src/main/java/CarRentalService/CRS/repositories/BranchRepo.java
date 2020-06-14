package CarRentalService.CRS.repositories;

import CarRentalService.CRS.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
}

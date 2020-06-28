package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Branch;

import java.util.List;

public interface BranchService {

    boolean createBranch(Branch branch) throws Exception;

    boolean updateBranch(Branch branch);

    boolean setBranchStatus(Long branchId) throws Exception;

    List<Branch> getAllBranches();

    Branch getById(Long branchId);
}

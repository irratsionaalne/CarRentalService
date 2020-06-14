package CarRentalService.crs.services;

import CarRentalService.crs.models.Branch;

import java.util.List;

public interface BranchService {

    boolean createBranch(Branch branch);

    boolean updateBranch(Branch branch);

    boolean deleteBranch(Long branchId);

    boolean restoreBranch(Long branchId);

    List<Branch> getAllBranches();

    Branch getById(Long branchId);
}

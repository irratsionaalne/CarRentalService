package CarRentalService.CRS.services;

import CarRentalService.CRS.models.Branch;

import java.util.List;

public interface BranchService {

    boolean createBranch(Branch branch) throws Exception;

    boolean updateBranch(Branch branch);

    boolean deleteBranch(Long branchId) throws Exception;

    boolean restoreBranch(Long branchId) throws Exception;

    List<Branch> getAllBranches();

    Branch getById(Long branchId);
}

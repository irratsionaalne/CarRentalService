package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Branch;
import CarRentalService.CRS.Repositories.BranchRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public boolean createBranch(Branch branch) throws Exception {
        if (branch == null) {
            throw new Exception("Invalid branch");
        }

        branch.setActive(true);
        branchRepo.save(branch);
        return true;
    }

    @Override
    public boolean updateBranch(Branch branch) {
        if (branch == null || !branchRepo.existsById(branch.getId())) {
            return false;
        }

        branchRepo.saveAndFlush(branch);
        return true;
    }

    @Override
    public boolean deleteBranch(Long branchId) throws Exception {
        Branch branch = getById(branchId);
        if (branch == null) {
            throw new Exception("Branch does not exist");
        }
        branch.setActive(false);
        updateBranch(branch);
        return true;
    }

    @Override
    public boolean restoreBranch(Long branchId) throws Exception {
        Branch branch= getById(branchId);
        if (branch == null) {
            throw new Exception("Branch does not exist");
        }

        branch.setActive(true);
        updateBranch(branch);
        return true;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public Branch getById(Long branchId) {
        return branchRepo.getOne(branchId);
    }
}

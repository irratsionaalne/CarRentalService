package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Booking;
import CarRentalService.CRS.Models.Branch;
import CarRentalService.CRS.Repositories.BranchRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public boolean createBranch(Branch branch) {
        if (branch == null) {
            return false;
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
    public boolean deleteBranch(Long branchId) {
        Branch branch = getById(branchId);
        if (branch == null) {
            return false;
        }
        branch.setActive(false);
        updateBranch(branch);
        return true;
    }

    @Override
    public boolean restoreBranch(Long branchId) {
        Branch branch= getById(branchId);
        if (branch == null) {
            return false;
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

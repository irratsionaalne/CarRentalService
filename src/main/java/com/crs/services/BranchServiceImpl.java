package com.crs.services;

import com.crs.models.Branch;
import com.crs.repositories.BranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public boolean setBranchStatus(Long branchId) throws Exception {
        Branch branch = getById(branchId);
        if (branch == null) {
            throw new Exception("Branch does not exist");
        }
        if (branch.isActive()) {
            branch.setActive(false);
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

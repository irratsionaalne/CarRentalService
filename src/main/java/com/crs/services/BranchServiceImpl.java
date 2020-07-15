
package com.crs.services;

import com.crs.models.*;
import com.crs.repositories.BranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public boolean createBranch(Branch branch) {
        branch.setActive(true);
        branchRepo.save(branch);
        return true;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public Branch getById(UUID branchId) {
        return branchRepo.getOne(branchId);
    }

    public boolean doesBranchExist(String streetAddress,String city){

        return branchRepo.findByCityAndStreetAddress(city,streetAddress).isPresent();
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
    public boolean deleteBranchById(UUID branchId) {
        Branch branch = getById(branchId);
        if (branchId == null) {
            return false;
        }
        branch.setActive(false);
        updateBranch(branch);
        return true;
    }

    @Override
    public boolean restoreBranchById(UUID branchId) {
        Branch branch = getById(branchId);
        if (branchId == null) {
            return false;
        }
        branch.setActive(true);
        updateBranch(branch);
        return true;
    }

}

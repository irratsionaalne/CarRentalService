
package com.crs.services;

import com.crs.dto.BranchCreationDto;
import com.crs.models.*;
import com.crs.repositories.BranchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public Branch createBranch(BranchCreationDto branchCreationDto) throws Exception {
        Branch branch = new Branch();
        branch.setStreetAddress(branchCreationDto.getStreetAddress());
        branch.setCity(branchCreationDto.getCity());
        branch.setActive(true);
        return branchRepo.save(branch);
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
        branch.setActive(true);
        branchRepo.saveAndFlush(branch);
        return true;
    }
}

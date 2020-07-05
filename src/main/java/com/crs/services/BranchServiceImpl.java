
package com.crs.services;

import com.crs.controllers.dto.BranchCreationDto;
import com.crs.models.*;
import com.crs.repositories.BranchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepo branchRepo;

    @Override
    public Branch createBranch(BranchCreationDto branchCreationDto) throws Exception {
        Branch branch = new Branch();
        branch.setStreetAddress(branchCreationDto.getStreetAddress());
        branch.setCity(branchCreationDto.getCity());
        return branchRepo.save(branch);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public Branch getById(Long branchId) {
        return branchRepo.getOne(branchId);
    }

    public boolean doesBranchExist(String streetAddress,String city){

        return branchRepo.findByCityAndStreetAddress(city,streetAddress).isPresent();
    }
}

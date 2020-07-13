package com.crs.services;

import com.crs.dto.BranchCreationDto;
import com.crs.models.Branch;

import java.util.List;
import java.util.UUID;

public interface BranchService {

    boolean createBranch(BranchCreationDto branchCreationDto) throws Exception;

    //boolean setBranchStatus(UUID branchId) throws Exception;

    List<Branch> getAllBranches();

    boolean doesBranchExist(String streetAddress,String city);

    Branch getById(UUID branchId);

    boolean updateBranch(Branch branch);

    boolean deleteBranchById(UUID branchId);

    boolean restoreBranchById(UUID branchId);
}

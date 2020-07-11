package com.crs.services;

import com.crs.dto.BranchCreationDto;
import com.crs.models.Branch;

import java.util.List;

public interface BranchService {

    Branch createBranch(BranchCreationDto branchCreationDto) throws Exception;

    //boolean setBranchStatus(Long branchId) throws Exception;

    List<Branch> getAllBranches();

    boolean doesBranchExist(String streetAddress,String city);

    Branch getById(Long branchId);
}

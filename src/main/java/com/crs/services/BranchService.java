package com.crs.services;

import com.crs.controllers.dto.BranchCreationDto;
import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.models.Branch;

import java.util.List;

public interface BranchService {

    Branch createBranch(BranchCreationDto branchCreationDto) throws Exception;

    //boolean setBranchStatus(Long branchId) throws Exception;

    List<Branch> getAllBranches();

    boolean doesBranchExist(String streetAddress,String city);

    Branch getById(Long branchId);
}

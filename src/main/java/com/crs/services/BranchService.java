package com.crs.services;

import com.crs.models.Branch;

import java.util.List;
import java.util.UUID;

public interface BranchService {

    boolean createBranch(Branch branch) throws Exception;

    List<Branch> getAllBranches();

    boolean doesBranchExist(String streetAddress,String city);

    Branch getById(UUID branchId);

    boolean updateBranch(Branch branch);

    boolean deleteBranchById(UUID branchId);

    boolean restoreBranchById(UUID branchId);
}

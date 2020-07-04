package com.crs.controllers;

import com.crs.models.Branch;
import com.crs.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showAllBranches(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "show-all-branches";
    }

    @GetMapping("/add")
    public String addBranchForm(Model model) {
        return "add-branch";
    }

    @PostMapping("/add")
    public String addBranch(Branch branch, Model model) throws Exception {
        boolean createResult = branchService.createBranch(branch);

        if (createResult) {
            model.addAttribute("message", "Branch has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllBranches(model);
        }
        model.addAttribute("branch", branch);
        model.addAttribute("message", "Error in creating a branch.");
        model.addAttribute("messageType", "error");
        return addBranchForm(model);

    }

    @GetMapping("/update")
    public String updateBranchForm(Model model) {
        return "update-branch";
    }

    @PutMapping("/update/{id}")
    public String updateBranch(@PathVariable("id") Long branchId, Branch branch, Model model) {
        branch.setId(branchId);
        boolean updateResult = branchService.updateBranch(branch);

        if (updateResult) {
            model.addAttribute("message", "Branch has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllBranches(model);
        }
        model.addAttribute("branch", branch);
        model.addAttribute("message", "Error in updating branch");
        model.addAttribute("messageType", "error");
        return updateBranchForm(model);

    }

    @PutMapping("/delete/{id}")
    public String setBranchStatus(@PathVariable("id") Long branchId, Model model) throws Exception {
        boolean deleteResult = branchService.setBranchStatus(branchId);

        if (deleteResult) {
            model.addAttribute("message", "Branch has been successfully deleted");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in cancelling branch.");
        model.addAttribute("messageType", "error");


        return showAllBranches(model);
    }

}
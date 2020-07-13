package com.crs.controllers;

import com.crs.models.Branch;
import com.crs.services.BranchService;
import com.crs.services.BranchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private  BranchService branchService;

    @GetMapping
    public ModelAndView showAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        ModelAndView modelAndView = new ModelAndView("branch/listofbranches");
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }


    @GetMapping("/update/{id}")
    public String updateBranchForm(@PathVariable("id") UUID branchId, @ModelAttribute("messageType") String messageType,
                                   @ModelAttribute("message") String message, Model model) {
        Branch branch = branchService.getById(branchId);
        if (branch == null) {
            throw new IllegalArgumentException("Branch with this ID not found!");
        }
        model.addAttribute("branch", branch);

        return "branch/branch-update";
    }

    @PostMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") UUID branchId, Branch branch, Model model) throws Exception {
        branch.setId(branchId);
        boolean updateResult = branchService.updateBranch(branch);
        if (updateResult) {
            model.addAttribute("message", "Branch has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllBranches();
        }
        model.addAttribute("branch", branch);
        model.addAttribute("message", "Error in updating a Branch");
        model.addAttribute("messageType", "error");
        return "redirect:/car/update/{id}";
    }

    /*
    @ModelAttribute("branch")
    public BranchCreationDto branchCreationDto() {
        return new BranchCreationDto();
    }


    @GetMapping("/add-branch")
    public String addBranchForm(Model model) {
        return "branch/add-branch";
    }

    @PostMapping("/add-branch")
    public Object addBranch(@ModelAttribute("branch") @Valid BranchCreationDto branchCreationDto) throws Exception {
        Branch branch = branchService.createBranch(branchCreationDto);
        if (branch != null) {
            return "redirect:/branch";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Error in creating a branch!");
        modelAndView.addObject("messageType", "error");
        modelAndView.setViewName("branch/add-branch");
        return modelAndView;

    }


    /*@PutMapping("/delete/{id}")
    public String setBranchStatus(@PathVariable("id") UUID branchId, Model model) throws Exception {
        boolean deleteResult = branchService.setBranchStatus(branchId);
        if (deleteResult) {
            model.addAttribute("message", "Branch has been successfully deleted");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in cancelling branch.");
        model.addAttribute("messageType", "error");
        return showAllBranches(model);
    }*/

}

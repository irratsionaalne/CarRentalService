package com.crs.controllers;

import com.crs.controllers.dto.BranchCreationDto;
import com.crs.controllers.dto.CustomerRegistrationDto;
import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.models.Branch;
import com.crs.models.Employee;
import com.crs.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public ModelAndView showAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        ModelAndView modelAndView = new ModelAndView("branch/listofbranches");
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

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
    public String setBranchStatus(@PathVariable("id") Long branchId, Model model) throws Exception {
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

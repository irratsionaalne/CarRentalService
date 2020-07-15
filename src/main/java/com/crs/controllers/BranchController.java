package com.crs.controllers;

import com.crs.models.Branch;
import com.crs.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private  BranchService branchService;

    @ModelAttribute("branch")
    public Branch branch() {
        return new Branch();
    }

    @GetMapping
    public ModelAndView showAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        ModelAndView modelAndView = new ModelAndView("branch/listofbranches");
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

    @GetMapping("/add-branch")
    public String showRegistrationForm() {
        return "branch/add-branch";
    }

    @PostMapping("/add-branch")
    public String registerBranch(@ModelAttribute("branch") @Valid Branch branch,
                                 BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if(branchService.doesBranchExist(branch.getStreetAddress(),branch.getCity())) {
            result.rejectValue("streetaddress", null, "There is already a branch with that address");
            result.rejectValue("city", null, "There is already a branch with that address");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("branch", branch);
            redirectAttributes.addFlashAttribute("message", "Error in creating a branch!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "branch/add-branch";
        }

        branchService.createBranch(branch);
        redirectAttributes.addFlashAttribute("message", "Branch has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/branch";
    }

    @GetMapping("/update/{id}")
    public String updateBranchForm(@PathVariable("id") UUID branchId, Model model) {
        Branch branch = branchService.getById(branchId);
        if (branch == null) {
            throw new IllegalArgumentException("Branch with this ID not found!");
        }
        model.addAttribute("branch", branch);

        return "branch/branch-update";
    }

    @PostMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") UUID branchId, Branch branch, Model model) {
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
        return "redirect:/branch/update/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") UUID branchId, RedirectAttributes redirectAttributes) {
        boolean deleteResult = branchService.deleteBranchById(branchId);
        if (deleteResult) {
            redirectAttributes.addFlashAttribute("message", "Branch #" + branchId + " has been inactivated.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/branch";
    }

    @GetMapping("/restore/{id}")
    public String restoreBranch(@PathVariable("id") UUID branchId, RedirectAttributes redirectAttributes) {
        boolean restoreResult = branchService.restoreBranchById(branchId);
        if (restoreResult) {
            redirectAttributes.addFlashAttribute("message", "Branch #" + branchId + " has been successfully restored.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in restoring branch #" + branchId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/branch";
    }

}

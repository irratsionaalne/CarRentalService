package com.crs.controllers;

import com.crs.models.Branch;
import com.crs.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-branch")
public class BranchCreationController {
    @Autowired
    private  BranchService branchService;

    @ModelAttribute("branch")
    public Branch branch() {
        return new Branch();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "branch/add-branch";
    }

    @PostMapping
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

}

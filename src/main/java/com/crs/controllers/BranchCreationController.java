package com.crs.controllers;


import com.crs.controllers.dto.BranchCreationDto;
import com.crs.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-branch")
@RequiredArgsConstructor

public class BranchCreationController {

    private final BranchService branchService;

    @ModelAttribute("branch")
    public BranchCreationDto branchCreationDto() {
        return new BranchCreationDto();
    }

    @GetMapping
    public String showRegistrationForm(@ModelAttribute("messageType") String messageType,
                                       @ModelAttribute("message") String message, Model model) {
        return "branch/add-branch";
    }

    @PostMapping
    public String registerBranch(@ModelAttribute("branch") @Valid BranchCreationDto branchCreationDto,
                                 BindingResult result, RedirectAttributes redirectAttributes) throws Exception {


        if(branchService.doesBranchExist(branchCreationDto.getStreetAddress(),branchCreationDto.getCity())) {
            result.rejectValue("streetaddress", null, "There is already a branch with that address");
            result.rejectValue("city", null, "There is already a branch with that address");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("branch", branchCreationDto);
            redirectAttributes.addFlashAttribute("message", "Error in creating a branch!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "branch/add-branch";
        }

        branchService.createBranch(branchCreationDto);
        redirectAttributes.addFlashAttribute("message", "Branch has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/branch";
    }

}

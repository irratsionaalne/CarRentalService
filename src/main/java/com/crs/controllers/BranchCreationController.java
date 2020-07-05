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
    public String showRegistrationForm(Model model) {
        return "branch/add-branch";
    }

    @PostMapping
    public String registerBranch(@ModelAttribute("branch") @Valid BranchCreationDto branchCreationDto,
                                 BindingResult result) throws Exception {


        if(branchService.doesBranchExist(branchCreationDto.getStreetAddress(),branchCreationDto.getCity())) {
            result.rejectValue("streetaddress", null, "There is already a branch with that address");
            result.rejectValue("city", null, "There is already a branch with that address");
        }

        if (result.hasErrors()) {
            return "branch/add-branch";
        }

        branchService.createBranch(branchCreationDto);
        return "branch";
    }

}

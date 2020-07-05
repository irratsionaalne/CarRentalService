package com.crs.controllers;


import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.EmployeeRole;
import com.crs.models.User;
import com.crs.repositories.UserRepo;
import com.crs.services.EmployeeService;
import com.crs.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeRegistrationController {


    private final UserServiceImpl userService;

    private final EmployeeService employeeService;


    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {
        return new EmployeeRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "employee/add-employee";
    }

    @PostMapping
    public String registerEmployee(@ModelAttribute("employee") @Valid EmployeeRegistrationDto employeeRegistrationDto,
                                   EmployeeRole employeeRole,
                                   BindingResult result) throws Exception {

        log.info("DTO {}", employeeRegistrationDto);
        if(userService.doesUserExist(employeeRegistrationDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            return "employee/add-employee";
        }

        employeeService.createEmployee(employeeRegistrationDto);
        return "login";
    }
}

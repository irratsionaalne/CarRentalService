package com.crs.controllers;


import com.crs.controllers.dto.CustomerRegistrationDto;
import com.crs.controllers.dto.UserRegistrationDto;
import com.crs.models.User;
import com.crs.repositories.UserRepo;
import com.crs.services.CustomerService;
import com.crs.services.UserServiceImpl;
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
@RequestMapping("/registration")
@RequiredArgsConstructor
public class CustomerRegistrationController {


    private final UserServiceImpl userService;

    private final CustomerService customerService;


    @ModelAttribute("customer")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerCustomer(@ModelAttribute("customer") @Valid CustomerRegistrationDto customerRegistrationDto,
                                      BindingResult result) throws Exception {

        if(userService.doesUserExist(customerRegistrationDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        customerService.createCustomer(customerRegistrationDto);
        return "login";
    }
}

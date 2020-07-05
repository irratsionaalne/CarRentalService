package com.crs.controllers;

import com.crs.controllers.dto.CustomerRegistrationDto;
import com.crs.services.CustomerService;
import com.crs.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class WebController {

    @GetMapping
    public String login(){
        return "home";
    }

   /* @GetMapping
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject(user);
        return modelAndView;
    }*/


    private final UserServiceImpl userService;

    private final CustomerService customerService;


    @ModelAttribute("customer")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping("login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    /*@GetMapping("office")
    public String showOfficeForm(Model model) {
        return "office";
    }*/

    /* @GetMapping("branch")
    public String showBranchForm(Model model) {
        return "branch";
    }*/

    /*@GetMapping("car")
    public String showCarForm(Model model) {
        return "car";
    }*/

    @GetMapping("rental")
    public String showRentalForm(Model model) {
        return "rental";
    }

    @GetMapping("return")
    public String showReturnForm(Model model) {
        return "return";
    }

    //office
    //branch

    @PostMapping("registration")
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

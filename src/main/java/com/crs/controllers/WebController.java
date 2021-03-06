package com.crs.controllers;

import com.crs.dto.CustomerRegistrationDto;
import com.crs.services.CustomerService;
import com.crs.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WebController {

    @Autowired
    private  UserServiceImpl userService;
    @Autowired
    private  CustomerService customerService;

    @GetMapping("/bo-home")
    public String index() {
        return "bo-home";
    }

    @GetMapping
    public String home() {
        return "home";
    }


    /*@GetMapping
    public String login(){
        return "home";
    }
   /* @GetMapping
    public ModelAndView index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bo-home");
        modelAndView.addObject(user);
        return modelAndView;
    }*/




    @ModelAttribute("customer")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping("login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("registration")
    public String showRegistrationForm(@ModelAttribute("messageType") String messageType,
                                       @ModelAttribute("message") String message, Model model) {
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

    /*@GetMapping("rental")
    public String showRentalForm(Model model) {
        return "rental";
    }

    @GetMapping("return")
    public String showReturnForm(Model model) {
        return "return";
    }*/

    //office
    //branch

    @PostMapping("registration")
    public String registerCustomer(@ModelAttribute("customer") @Valid CustomerRegistrationDto customerRegistrationDto,
                                   BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if(userService.doesUserExist(customerRegistrationDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customerRegistrationDto);
            redirectAttributes.addFlashAttribute("message", "Error in creating customer!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "registration";
        }

        customerService.createCustomer(customerRegistrationDto);
        redirectAttributes.addFlashAttribute("message", "Customer has been successfully registered.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/login";
    }


}

package com.crs.controllers;

import com.crs.models.User;
import com.crs.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @Autowired
    private  UserServiceImpl userService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/bo-home")
    public String index() {
        return "bo-home";
    }

    @GetMapping("login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("office")
    public String showOfficeView() {
        return "office";
    }

    @GetMapping("registration")
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("registration")
    public String registerCustomer(@ModelAttribute("user") User user,
                                   BindingResult result, RedirectAttributes redirectAttributes) {

        if(userService.doesUserExist(user.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "Error in creating customer!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "registration";
        }

        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "Customer has been successfully registered.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/login";
    }

}

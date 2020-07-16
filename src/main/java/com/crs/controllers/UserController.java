package com.crs.controllers;

import com.crs.models.User;
import com.crs.services.UserServiceImpl;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ModelAndView showAllUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("user/listofusers");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "user/add-user";
    }

    @PostMapping("/add")
    public Object addUser(@ModelAttribute("user") User user,
                          BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "Error in creating a user!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "user/add-user";
        }

        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "User has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/user";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(Model model) {
        return "update-user";
    }

    @PutMapping("/update/{id}")
    public Object updateUser(@PathVariable("id") UUID userId, User user, Model model) throws Exception {
        user.setId(userId);
        boolean updateResult = userService.updateUser(user);

        if (updateResult) {
            model.addAttribute("message", "Customer has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllUsers();
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "Error in updating customer");
        model.addAttribute("messageType", "error");
        return updateUserForm(model);
    }


}


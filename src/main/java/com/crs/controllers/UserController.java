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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public ModelAndView showAllUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "user/add";
    }

    @PostMapping("/add")
    public Object addUser(@ModelAttribute("user") User user,
                          BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "Error in creating a user!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "user/add";
        }

        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "User has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/user";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") UUID userId, Model model) {
        User user = userService.getById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with this ID not found!");
        }
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/update/{id}")
    public Object updateUser(@PathVariable("id") UUID userId, User user, Model model) {
        user.setId(userId);
        boolean updateResult = userService.updateUser(user);
        if (updateResult) {
            model.addAttribute("message", "User has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllUsers();
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "Error in updating a User");
        model.addAttribute("messageType", "error");
        return "redirect:/user/update/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID userId, RedirectAttributes redirectAttributes) {
        boolean deleteResult = userService.deleteUserById(userId);
        if (deleteResult) {
            redirectAttributes.addFlashAttribute("message", "User #" + userId + " has been removed.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/user";
    }

    @GetMapping("/restore/{id}")
    public String restoreUser(@PathVariable("id") UUID userId, RedirectAttributes redirectAttributes) {
        boolean restoreResult = userService.restoreUserById(userId);
        if (restoreResult) {
            redirectAttributes.addFlashAttribute("message", "User #" + userId + " has been successfully restored.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in restoring user #" + userId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/user";
    }

}


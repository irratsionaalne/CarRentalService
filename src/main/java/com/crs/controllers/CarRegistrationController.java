package com.crs.controllers;


import com.crs.models.Car;
import com.crs.services.CarService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/add-car")
public class CarRegistrationController {
    @Autowired
    private CarService carService;

    @ModelAttribute("car")
    public Car car() {
        return new Car();
    }

    @GetMapping
    public String showCarForm(@ModelAttribute("messageType") String messageType,
                              @ModelAttribute("message") String message, Model model) {
        return "car/add-car";
    }

    @PostMapping
    public String registerCar(@ModelAttribute("car") @Valid Car car,
                              BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("message", "Error in creating a car!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "car/add-car";
        }

        carService.createCar(car);
        redirectAttributes.addFlashAttribute("message", "Car has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/car";
    }

}

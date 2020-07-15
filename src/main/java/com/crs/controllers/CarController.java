package com.crs.controllers;

import com.crs.models.Car;
import com.crs.services.CarService;
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
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;
    @ModelAttribute
    public Car car() {
        return new Car();
    }

    @GetMapping
    public ModelAndView showAllCars() {
        List<Car> cars = carService.getAllCars();
        ModelAndView modelAndView = new ModelAndView("car/listofcars");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String updateCarForm(@PathVariable("id") UUID carId, @ModelAttribute("messageType") String messageType,
                                @ModelAttribute("message") String message, Model model) {
        Car car = carService.getById(carId);
        if (car == null) {
            throw new IllegalArgumentException("Car with this ID not found!");
        }
        model.addAttribute("car", car);

        return "update-car";
    }

    @PostMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") UUID carId, Car car, Model model) throws Exception {
        car.setId(carId);
        boolean updateResult = carService.updateCar(car);
        if (updateResult) {
            model.addAttribute("message", "Car has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllCars();
        }
        model.addAttribute("car", car);
        model.addAttribute("message", "Error in updating car");
        model.addAttribute("messageType", "error");
        return "redirect:/car";
    }

    @GetMapping("/add-car")
    public String showCarRegistrationForm() {
        return "car/add-car";
    }

    @PostMapping("/add-car")
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

package com.crs.controllers;

import com.crs.controllers.dto.CarDto;
import com.crs.models.Car;
import com.crs.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ModelAndView showAllCars() {
        List<Car> cars = carService.getAllCars();
        ModelAndView modelAndView = new ModelAndView("car/listofcars");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String updateCarForm(@PathVariable("id") Long carId, @ModelAttribute("messageType") String messageType,
                                @ModelAttribute("message") String message, Model model) {
        Car car = carService.getById(carId);
        if (car == null) {
            throw new IllegalArgumentException("Car with this ID not found!");
        }
        model.addAttribute("car", car);

        return "car/car-update";
    }

    @PostMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") Long carId, Car car, Model model) throws Exception {
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


    /*
    @ModelAttribute("car")
    public CarDto carDto() {
        return new CarDto();
    }
    @GetMapping("/add-car")
    public String addCarForm(Model model) {
        return "car/add-car";
    }
    @PostMapping("/add-car")
    public Object addCar(@ModelAttribute("car") @Valid CarDto carDto) throws Exception {
        Car car = carService.createCar(carDto);
        if (car != null) {
            return "redirect:/login";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Error in creating a car!");
        modelAndView.addObject("messageType", "error");
        modelAndView.setViewName("car/add-car");
        return modelAndView;
    }
    @GetMapping("/update")
    public String updateCarForm(Model model) {
        return "update-car";
    }
    @PutMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") Long carId, Car car, Model model) throws Exception {
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
        return updateCarForm(model);
    }
     */

}

package com.crs.controllers;

import com.crs.controllers.dto.CarDto;
import com.crs.models.Car;
import com.crs.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @ModelAttribute("car")
    public CarDto carDto() {
        return new CarDto();
    }

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

        CarDto carDto = carService.convertToCarDto(car);
        model.addAttribute("car", carDto);

        return "car/car-update";
    }

    @PostMapping("/update/{id}")
    public Object updateCar(@PathVariable("id") Long carId, @ModelAttribute("car") @Valid CarDto carDto,
                            BindingResult result, Model model) throws Exception {
        Car car = carService.convertToCar(carDto);
        car.setId(carId);
        if (result.hasErrors()) {
            System.out.println("asdasdasdasd");
            return "car/car-update";
        }
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

}

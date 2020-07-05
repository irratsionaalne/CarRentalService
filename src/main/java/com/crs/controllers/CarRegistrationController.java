package com.crs.controllers;

import com.crs.controllers.dto.CarDto;
import com.crs.controllers.dto.EmployeeRegistrationDto;
import com.crs.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-car")
@RequiredArgsConstructor
@Slf4j
public class CarRegistrationController {

    private final CarService carService;

    @ModelAttribute("car")
    public CarDto carDto() {
        return new CarDto();
    }

    @GetMapping
    public String showCarForm(Model model) {
        return "car/add-car";
    }

    @PostMapping
    public String registerCar(@ModelAttribute("car") @Valid CarDto carDto,
                                   BindingResult result) throws Exception {

        log.info("CAR DTO {}", carDto);

        if (result.hasErrors()) {
            return "car/add-car";
        }

        carService.createCar(carDto);
        return "login";
    }

}

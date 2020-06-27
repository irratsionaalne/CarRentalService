package CarRentalService.CRS.Controllers;


import CarRentalService.CRS.Models.Car;
import CarRentalService.CRS.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("")
    public String showAllCars(@ModelAttribute("messageType") String messageType, @ModelAttribute("message") String message,
                              Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "car/car-list";
    }

    @GetMapping("/add")
    public String addCarForm(@ModelAttribute("car") Car car, @ModelAttribute("messageType") String messageType,
                             @ModelAttribute("message") String message) {
        return "car/car-add";
    }

    @PostMapping("/add")
    public String addCar(@Valid Car car, RedirectAttributes redirectAttributes) throws Exception {
        boolean createResult = carService.createCar(car);

        if (createResult) {
            redirectAttributes.addFlashAttribute("message", "Car has been successfully created.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car/";
        } else {
            redirectAttributes.addFlashAttribute("car", car);
            redirectAttributes.addFlashAttribute("message", "Error in creating a car!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/car/add";
        }
    }

    @GetMapping("/update/{id}")
    public String updateCarForm(@PathVariable("id") Long carId, @RequestParam(value = "car", required = false) Car car,
                                @ModelAttribute("messageType") String messageType,
                                @ModelAttribute("message") String message, Model model) {
        if (car == null) {
            model.addAttribute("car", carService.getById(carId));
        }
        return "car/car-update";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id") Long carId, @Valid Car car, RedirectAttributes redirectAttributes) throws Exception {
        car.setId(carId);
        boolean updateResult = carService.updateCar(car);

        if (updateResult) {
            redirectAttributes.addFlashAttribute("message", "Car #" + carId + " has been successfully updated.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car/";
        } else {
            redirectAttributes.addAttribute("id", carId);
            redirectAttributes.addAttribute("car", car);
            redirectAttributes.addFlashAttribute("message", "Error in updating this car #" + carId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/car/update/{id}";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long carId, RedirectAttributes redirectAttributes) throws Exception {
        boolean deleteResult = carService.deleteCar(carId);

        if (deleteResult) {
            redirectAttributes.addFlashAttribute("message", "Car #" + carId + " has been successfully deleted.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in deleting car #" + carId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/car/";
    }

    @GetMapping("/restore/{id}")
    public String restoreCar(@PathVariable("id") Long carId, RedirectAttributes redirectAttributes) throws Exception {
        boolean restoreResult = carService.restoreCar(carId);

        if (restoreResult) {
            redirectAttributes.addFlashAttribute("message", "Car #" + carId + " has been successfully restored.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in restoring car #" + carId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/car/";
    }

}


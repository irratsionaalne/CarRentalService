package CarRentalService.CRS.Controllers;

import CarRentalService.CRS.Models.CarRental;
import CarRentalService.CRS.Services.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car-rental")

public class CarRentalController {

    @Autowired
    private CarRentalService carRentalService;

    @GetMapping
    public String showAllCarRents(Model model) {
        List<CarRental> carRental = carRentalService.getAllCarRentals();
        model.addAttribute("carRental", carRental);
        return "show-all-car-rentals";
    }

    @GetMapping("/add")
    public String addCarRentalForm(Model model) {
        return "add-car-rental";
    }


    @PostMapping("/add")
    public String addCarRental(CarRental carRental, Model model) throws Exception {
        carRental.setActive(true);
        boolean createResult = carRentalService.createCarRental(carRental);

        if (createResult) {
            model.addAttribute("message", "Car has been successfully rented.");
            model.addAttribute("messageType", "success");
            return showAllCarRents(model);
        }
        model.addAttribute("car-rental", carRental);
        model.addAttribute("message", "Error renting a car!");
        model.addAttribute("messageType", "error");
        return addCarRentalForm(model);

    }

    @GetMapping("/update")
    public String updateCarRentalForm(Model model) {
        return "update-car-rental";
    }

    @PutMapping("/update/{id}")
    public String updateCarRental(@PathVariable("id") Long carRentalId, CarRental carRental, Model model) throws Exception {
        carRental.setId(carRentalId);
        boolean updateResult = carRentalService.updateCarRental(carRental);

        if (updateResult) {
            model.addAttribute("message", "Car rent has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllCarRents(model);
        }
        model.addAttribute("car-rent", carRental);
        model.addAttribute("message", "Error in updating a car rent!");
        model.addAttribute("messageType", "error");
        return updateCarRentalForm(model);

    }

    @GetMapping("/delete/{id}")
    public String setCarRentalStatus(@PathVariable("id") Long carRentalId, Model model) throws Exception {
        boolean deleteResult = carRentalService.setCarRentalStatus(carRentalId);

        if (deleteResult) {
            model.addAttribute("message", "Car rent order has been successfully deleted.");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in deleting a car rent order!");
        model.addAttribute("messageType", "error");


        return showAllCarRents(model);
    }


}

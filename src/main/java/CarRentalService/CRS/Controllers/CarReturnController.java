package CarRentalService.CRS.Controllers;

import CarRentalService.CRS.Models.CarReturn;
import CarRentalService.CRS.Services.CarReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/car-return")

public class CarReturnController {
    @Autowired
    private CarReturnService carReturnService;

    @GetMapping
    public String showAllCarReturns(Model model) {
        List<CarReturn> carReturn = carReturnService.getAllCarReturns();
        model.addAttribute("carReturn", carReturn);
        return "show-all-car-returns";
    }

    @GetMapping("/add")
    public String addCarReturnForm(Model model) {
        return "add-car-return";
    }


    @PostMapping("/add")
    public String addCarReturn(CarReturn carReturn, Model model) throws Exception {
        carReturn.setActive(true);
        boolean createResult = carReturnService.createCarReturn(carReturn);

        if (createResult) {
            model.addAttribute("message", "Car has been successfully returned.");
            model.addAttribute("messageType", "success");
            return showAllCarReturns(model);
        } else {
            model.addAttribute("car-rental", carReturn);
            model.addAttribute("message", "Error returning a car!");
            model.addAttribute("messageType", "error");
            return addCarReturnForm(model);
        }
    }

    @GetMapping("/update")
    public String updateCarReturnForm(Model model) {
        return "update-car-return";
    }

    @PostMapping("/update/{id}")
    public String updateCarReturn(@PathVariable("id") Long carReturnId, CarReturn carReturn, Model model) throws Exception {
        carReturn.setId(carReturnId);
        boolean updateResult = carReturnService.updateCarReturn(carReturn);

        if (updateResult) {
            model.addAttribute("message", "Car return has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllCarReturns(model);
        } else {
            model.addAttribute("car-rent", carReturn);
            model.addAttribute("message", "Error in updating a car return!");
            model.addAttribute("messageType", "error");
            return updateCarReturnForm(model);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCarReturn(@PathVariable("id") Long carReturnId, Model model) throws Exception {
        boolean deleteResult = carReturnService.deleteCarReturn(carReturnId);

        if (deleteResult) {
            model.addAttribute("message", "Car return has been successfully deleted.");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in deleting a car return!");
            model.addAttribute("messageType", "error");
        }

        return showAllCarReturns(model);
    }

    /*@GetMapping("/restore/{id}")
    public String restoreCarReturn(@PathVariable("id") Long carReturnId, Model model) throws Exception {
        boolean deleteResult = carReturnService.restoreCarReturn(carReturnId);

        if (deleteResult) {
            model.addAttribute("message", "Car rent has been successfully restored.");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in restoring a car rent!");
            model.addAttribute("messageType", "error");
        }

        return showAllCarReturns(model);
    }*/
}

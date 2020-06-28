package CarRentalService.CRS.Controllers;

import CarRentalService.CRS.Models.Office;
import CarRentalService.CRS.Services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @GetMapping
    public String showAllOffices(Model model) {
        List<Office> offices = officeService.getAllOffices();
        model.addAttribute("offices", offices);
        return "show-all-offices";
    }

    @GetMapping("/add")
    public String addOfficeForm(Model model) {
        return "add-office";
    }

    @PostMapping("/add")
    public String addOffice(Office office, Model model) throws Exception {
        boolean createResult = officeService.createOffice(office);

        if (createResult) {
            model.addAttribute("message", "Office has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllOffices(model);
        }
        model.addAttribute("office", office);
        model.addAttribute("message", "Error in creating a office.");
        model.addAttribute("messageType", "error");
        return addOfficeForm(model);

    }

    @GetMapping("/update")
    public String updateOfficeForm(Model model) {
        return "update-office";
    }

    @PutMapping("/update/{id}")
    public String updateOffice(@PathVariable("id") Long officeId, Office office, Model model) {
        office.setId(officeId);
        boolean updateResult = officeService.updateOffice(office);

        if (updateResult) {
            model.addAttribute("message", "Office has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllOffices(model);
        }
        model.addAttribute("office", office);
        model.addAttribute("message", "Error in updating office");
        model.addAttribute("messageType", "error");
        return updateOfficeForm(model);

    }

}

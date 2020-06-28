package CarRentalService.CRS.Controllers;


import CarRentalService.CRS.Controllers.Dto.UserRegistrationDto;
import CarRentalService.CRS.Models.Employee;
import CarRentalService.CRS.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        Employee existing = employeeService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("login", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        employeeService.save(userDto);
        return "redirect:/registration?success";
    }
}

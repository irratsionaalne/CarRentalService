package com.crs.controllers;

import com.crs.dto.EmployeeRegistrationDto;
import com.crs.services.EmployeeService;
import com.crs.services.UserServiceImpl;
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
@RequestMapping("/add-employee")
public class EmployeeRegistrationController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {
        return new EmployeeRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(@ModelAttribute("messageType") String messageType,
                                       @ModelAttribute("message") String message, Model model) {
        return "employee/add-employee";
    }

    @PostMapping
    public String registerEmployee(@ModelAttribute("employee") @Valid EmployeeRegistrationDto employeeRegistrationDto,
                                   BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if(userService.doesUserExist(employeeRegistrationDto.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("employee", employeeRegistrationDto);
            redirectAttributes.addFlashAttribute("message", "Error in creating an employee!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "employee/add-employee";
        }

        employeeService.createEmployee(employeeRegistrationDto);
        redirectAttributes.addFlashAttribute("message", "Employee has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/employee";
    }

}

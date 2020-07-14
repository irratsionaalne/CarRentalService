package com.crs.controllers;

import com.crs.models.Branch;
import com.crs.models.Employee;
import com.crs.models.User;
import com.crs.services.BranchService;
import com.crs.services.EmployeeService;
import com.crs.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BranchService branchService;

    @GetMapping
    public ModelAndView showAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView("employee/listofemployees");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/add")
    public String showRegistrationForm(@ModelAttribute("employee") Employee employee, @ModelAttribute("messageType") String messageType,
                                       @ModelAttribute("message") String message, Model model) {
        List<Branch> branches = branchService.getAllBranches().stream()
                .filter(Branch::isActive).collect(Collectors.toList());
        model.addAttribute("branches", branches);
        return "employee/add-employee";
    }

    @PostMapping("/add")
    public String registerEmployee(@ModelAttribute("employee") @Valid Employee employee, User user, @ModelAttribute("messageType") String messageType,
                                   @ModelAttribute("message") String message,
                                   BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if(userService.doesUserExist(user.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("employee", employee);
            redirectAttributes.addFlashAttribute("message", "Error in creating an employee!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "employee/add-employee";
        }

        employeeService.createEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Employee has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/employee";
    }

    /*

    @GetMapping("/update")
    public String updateEmployeeForm(Model model) {
        return "update-employee";
    }
    @PutMapping("/update/{id}")
    public Object updateEmployee(@PathVariable("id") UUID employeeId, Employee employee, Model model) {
        employee.setId(employeeId);
        boolean updateResult = employeeService.updateEmployee(employee);
        if (updateResult) {
            model.addAttribute("message", "Employee has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllEmployees();
        }
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Error in updating employee");
        model.addAttribute("messageType", "error");
        return updateEmployeeForm(model);
    }
     */

}

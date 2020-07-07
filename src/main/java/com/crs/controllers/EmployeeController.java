package com.crs.controllers;

import com.crs.models.Employee;
import com.crs.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ModelAndView showAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView("employee/listofemployees");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    /*
    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {
        return new EmployeeRegistrationDto();
    }
    @GetMapping("/add-employee")
    public String addEmployeeForm(Model model) {
        return "employee/add-employee";
    }
    @PostMapping("/add-employee")
    public Object addEmployee(@ModelAttribute("employee") @Valid EmployeeRegistrationDto employeeRegistrationDto) throws Exception {
        Employee employee = employeeService.createEmployee(employeeRegistrationDto);
        log.info("Logging addEmployee in Controller");
        if (employee != null) {
            return "redirect:/employee";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Error in creating an employee!");
        modelAndView.addObject("messageType", "error");
        modelAndView.setViewName("employee/add-employee");
        return modelAndView;
    }
    @GetMapping("/update")
    public String updateEmployeeForm(Model model) {
        return "update-employee";
    }
    @PutMapping("/update/{id}")
    public Object updateEmployee(@PathVariable("id") Long employeeId, Employee employee, Model model) {
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
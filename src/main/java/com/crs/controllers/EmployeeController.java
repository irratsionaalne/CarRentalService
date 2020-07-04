package com.crs.controllers;

import com.crs.models.Employee;
import com.crs.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ModelAndView showAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        ModelAndView modelAndView = new ModelAndView("employee/listofemployees");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }



    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        return "add-employee";
    }

    @PostMapping("/add")
    public Object addEmployee(Employee employee, Model model) throws Exception {
        boolean createResult = employeeService.createEmployee(employee);

        if (createResult) {
            model.addAttribute("message", "Employee has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllEmployees();
        }
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Error in creating a employee.");
        model.addAttribute("messageType", "error");
        return addEmployeeForm(model);

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

    @GetMapping("/delete/{id}")
    public ModelAndView setEmployeeStatus(@PathVariable("id") Long employeeId, Model model) throws Exception {
        boolean deleteResult = employeeService.setEmployeeStatus(employeeId);

        if (deleteResult) {
            model.addAttribute("message", "Employee has been successfully deleted");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in cancelling employee.");
        model.addAttribute("messageType", "error");

        return showAllEmployees();
    }

}

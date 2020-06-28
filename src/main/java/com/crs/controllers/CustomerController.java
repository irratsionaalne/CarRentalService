package com.crs.controllers;


import com.crs.models.Customer;
import com.crs.models.Employee;
import com.crs.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView showAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        ModelAndView modelAndView = new ModelAndView("forms/listofcustomers");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        return "add-customer";
    }

    @PostMapping("/add")
    public Object addCustomer(Customer customer, Model model) throws Exception {
        boolean createResult = customerService.createCustomer(customer);

        if (createResult) {
            model.addAttribute("message", "Customer has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllCustomers();
        }
        model.addAttribute("customer", customer);
        model.addAttribute("message", "Error in creating a customer!");
        model.addAttribute("messageType", "error");
        return addCustomerForm(model);

    }

    @GetMapping("/update/{id}")
    public String updateCustomerForm(Model model) {
        return "update-customer";
    }

    @PutMapping("/update/{id}")
    public Object updateCustomer(@PathVariable("id") Long customerId, Customer customer, Model model) throws Exception {
        customer.setId(customerId);
        boolean updateResult = customerService.updateCustomer(customer);

        if (updateResult) {
            model.addAttribute("message", "Customer has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllCustomers();
        }
        model.addAttribute("customer", customer);
        model.addAttribute("message", "Error in updating customer");
        model.addAttribute("messageType", "error");
        return updateCustomerForm(model);

    }

    @GetMapping("/delete/{id}")
    public String setCustomerStatus(@PathVariable("id") Long customerId, Model model) throws Exception {
        boolean deleteResult = customerService.setCustomerStatus(customerId);

        if (deleteResult) {
            model.addAttribute("message", "Customer #" + customerId + " has been successfully deleted.");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in deleting customer #" + customerId + "!");
        model.addAttribute("messageType", "error");

        return "redirect:/customer/";
    }
}

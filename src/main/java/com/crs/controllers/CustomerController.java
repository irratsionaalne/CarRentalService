package com.crs.controllers;


import com.crs.models.Customer;
import com.crs.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String showAllCustomers(@ModelAttribute("messageType") String messageType, @ModelAttribute("message") String message,
                                   Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/customer-list";
    }

    @GetMapping("/add")
    public String addCustomerForm(@ModelAttribute("customer") Customer customer, @ModelAttribute("messageType") String messageType,
                                  @ModelAttribute("message") String message) {
        return "customer/customer-add";
    }

    @PostMapping("/add")
    public String addCustomer(Customer customer, RedirectAttributes redirectAttributes) throws Exception {
        boolean createResult = customerService.createCustomer(customer);

        if (createResult) {
            redirectAttributes.addFlashAttribute("message", "Customer has been successfully created.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer/";
        }
        redirectAttributes.addFlashAttribute("customer", customer);
        redirectAttributes.addFlashAttribute("message", "Error in creating a customer!");
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/customer/add";

    }

    @GetMapping("/update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long customerId, @RequestParam(value = "customer", required = false) Customer customer,
                                     @ModelAttribute("messageType") String messageType,
                                     @ModelAttribute("message") String message, Model model) {
        if (customer == null) {
            model.addAttribute("customer", customerService.getById(customerId));
        }
        return "customer/customer-update";
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long customerId, Customer customer, RedirectAttributes redirectAttributes) throws Exception {
        customer.setId(customerId);
        boolean updateResult = customerService.updateCustomer(customer);

        if (updateResult) {
            redirectAttributes.addFlashAttribute("message", "Customer #" + customerId + " has been successfully updated.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer/";
        }
        redirectAttributes.addAttribute("id", customerId);
        redirectAttributes.addAttribute("customer", customer);
        redirectAttributes.addFlashAttribute("message", "Error in updating this customer #" + customerId + "!");
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/customer/update/{id}";

    }

    @GetMapping("/delete/{id}")
    public String setCustomerStatus(@PathVariable("id") Long customerId, RedirectAttributes redirectAttributes) throws Exception {
        boolean deleteResult = customerService.setCustomerStatus(customerId);

        if (deleteResult) {
            redirectAttributes.addFlashAttribute("message", "Customer #" + customerId + " has been successfully deleted.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        }
        redirectAttributes.addFlashAttribute("message", "Error in deleting customer #" + customerId + "!");
        redirectAttributes.addFlashAttribute("messageType", "error");

        return "redirect:/customer/";
    }
}

package com.crs.controllers;

import com.crs.dto.CustomerRegistrationDto;
import com.crs.models.Customer;
import com.crs.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView showAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        ModelAndView modelAndView = new ModelAndView("customer/listofcustomers");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @ModelAttribute("customer")
    public CustomerRegistrationDto customerRegistrationDto() {
        return new CustomerRegistrationDto();
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        return "add-customer";
    }

    @PostMapping("/add-customer")
    public Object addCustomer(@ModelAttribute("customer") @Valid CustomerRegistrationDto customerRegistrationDto) throws Exception {
        Customer customer = customerService.createCustomer(customerRegistrationDto);

        if (customer != null) {
            return "redirect:/customer";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Error in creating a customer!");
        modelAndView.addObject("messageType", "error");
        modelAndView.setViewName("add-customer");
        return modelAndView;
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

}

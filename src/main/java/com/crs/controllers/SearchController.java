package com.crs.controllers;

import com.crs.models.Car;
import com.crs.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @GetMapping
    public String showRegistrationForm(@ModelAttribute("car") Car car)  {
        return "searchView";
    }

    @PostMapping
    public String search(LocalDate fromDate, LocalDate toDate, Car car, RedirectAttributes redirectAttributes) throws Exception {
        if (!searchService.searchForBooking(fromDate, toDate, car)) {
            redirectAttributes.addFlashAttribute("search", searchService);
            redirectAttributes.addFlashAttribute("message", "Car is not available for the selected dates. ");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "/booking";
        }
        return "searchView";


    }
}

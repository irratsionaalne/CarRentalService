package com.crs.controllers;

import com.crs.services.BookingService;
import com.crs.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/revenue")

public class RevenueController {
    @Autowired
    private SearchService searchService;


    @GetMapping
    public String revenue(Model model) {

        model.addAttribute("key", searchService.revenue());
        return "revenue/revenue";
    }

}

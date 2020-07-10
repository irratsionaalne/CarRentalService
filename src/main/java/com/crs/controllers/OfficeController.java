package com.crs.controllers;

import com.crs.models.Office;
import com.crs.services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OfficeController {

    @GetMapping("/office")
    public String office(){
        return "office";
    }

    /*private final OfficeService officeService;

    @GetMapping
    public String showAllOffices(Model model) {
        List<Office> offices = officeService.getAllOffices();
        model.addAttribute("offices", offices);
        return "office";
    }*/

    @GetMapping
    public String addOfficeForm(Model model) {
        return "office";
    }

}

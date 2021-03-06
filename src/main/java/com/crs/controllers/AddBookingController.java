package com.crs.controllers;

import com.crs.dto.BookingDto;
import com.crs.services.BookingService;
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
@RequestMapping("/add-booking")
public class AddBookingController {
    @Autowired
    private  BookingService bookingService;

    @ModelAttribute("booking")
    public BookingDto bookingDto() {
        return new BookingDto();
    }

    @GetMapping
    public String showBookingForm(@ModelAttribute("messageType") String messageType,
                              @ModelAttribute("message") String message, Model model) {
        return "booking/add-booking";
    }

    @PostMapping
    public String createBooking(@ModelAttribute("booking") @Valid BookingDto bookingDto,
                              BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("booking", bookingDto);
            redirectAttributes.addFlashAttribute("message", "Error in creating a booking!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "booking/add-booking";
        }

        bookingService.createBooking(bookingDto);
        redirectAttributes.addFlashAttribute("message", "Booking has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/booking";
    }

}

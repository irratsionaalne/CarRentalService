package com.crs.controllers;

import com.crs.models.Booking;
import com.crs.models.User;
import com.crs.services.BookingService;
import com.crs.services.UserService;
import com.crs.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ModelAndView showAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        ModelAndView modelAndView = new ModelAndView("booking/list");
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/bookingView")
    public String booking() {
        return "booking/bookingView";
    }

    @GetMapping("/add")
    public String addBookingForm() {
        return "booking/add";
    }

    @PostMapping("/add")
    public String addBooking(Booking booking, Model model) throws Exception {
        boolean createResult = bookingService.createBooking(booking);

        if (createResult) {
            model.addAttribute("message", "Booking has been successfully created.");
            model.addAttribute("messageType", "success");
            return "booking/list";
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in creating a booking.");
        model.addAttribute("messageType", "error");
        return "redirect:/booking";

    }

    @PutMapping("/update")
    public String updateBookingForm(Model model) {
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") UUID bookingId, Booking booking, Model model) throws Exception {
        booking.setId(bookingId);
        boolean updateResult = bookingService.updateBooking(booking);

        if (updateResult) {
            model.addAttribute("message", "Booking has been successfully updated.");
            model.addAttribute("messageType", "success");
            return "/booking/list";
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in updating booking");
        model.addAttribute("messageType", "error");
        return updateBookingForm(model);

    }



   /* @PutMapping("/delete/{id}")
    public String cancelBooking(@PathVariable("id") UUID bookingId, Model model) throws Exception {
        boolean deleteResult = bookingService.cancelBooking(bookingId);

        if (deleteResult) {
            model.addAttribute("message", "Booking has been successfully cancelled");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in cancelling booking.");
        model.addAttribute("messageType", "error");

        return showAllBookings(model);
    }*/



}

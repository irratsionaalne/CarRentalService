package com.crs.controllers;

import com.crs.models.Booking;
import com.crs.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        ModelAndView modelAndView = new ModelAndView("booking/listofbookings");
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/bookingView")
    public String booking() {
        return "booking/bookingView";
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
            return "/booking/listofbookings";
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in updating booking");
        model.addAttribute("messageType", "error");
        return updateBookingForm(model);

    }

    @GetMapping("/add")
    public String showBookingForm(@ModelAttribute("booking") Booking booking) {
        return "booking/add-booking";
    }

    @PostMapping("/add")
    public String createBooking(@ModelAttribute("booking") Booking booking,
                                BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("booking", booking);
            redirectAttributes.addFlashAttribute("message", "Error in creating a booking!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "booking/add-booking";
        }

        bookingService.createBooking(booking);
        redirectAttributes.addFlashAttribute("message", "Booking has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/booking";
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

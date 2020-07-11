package com.crs.controllers;

import com.crs.models.Booking;
import com.crs.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("booking")
@RequiredArgsConstructor
public class BookingController {




    private final BookingService bookingService;




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

    /*
    @GetMapping("/add")
    public String addBookingForm(Model model) {
        return "add-booking";
    }

    @PostMapping("/add")
    public String addBooking(Booking booking, Model model) throws Exception {
        boolean createResult = bookingService.createBooking(booking);

        if (createResult) {
            model.addAttribute("message", "Booking has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllBookings(model);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in creating a booking.");
        model.addAttribute("messageType", "error");
        return addBookingForm(model);

    }

    @PutMapping("/update")
    public String updateBookingForm(Model model) {
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Long bookingId, Booking booking, Model model) {
        booking.setId(bookingId);
        boolean updateResult = bookingService.updateBooking(booking);

        if (updateResult) {
            model.addAttribute("message", "Booking has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllBookings(model);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in updating booking");
        model.addAttribute("messageType", "error");
        return updateBookingForm(model);

    }

    @PutMapping("/delete/{id}")
    public String cancelBooking(@PathVariable("id") Long bookingId, Model model) throws Exception {
        boolean deleteResult = bookingService.cancelBooking(bookingId);

        if (deleteResult) {
            model.addAttribute("message", "Booking has been successfully cancelled");
            model.addAttribute("messageType", "success");
        }
        model.addAttribute("message", "Error in cancelling booking.");
        model.addAttribute("messageType", "error");

        return showAllBookings(model);
    }

     */


}

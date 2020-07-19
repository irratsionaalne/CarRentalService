package com.crs.controllers;

import com.crs.models.Booking;
import com.crs.models.Branch;
import com.crs.models.Car;
import com.crs.services.*;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private CarService carService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private SearchService searchService;


    @ModelAttribute("booking")
    public Booking booking() {
        return new Booking();
    }

    @GetMapping
    public ModelAndView showAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        ModelAndView modelAndView = new ModelAndView("booking/list");
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/employee")
    public ModelAndView showAllEmployeeBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        ModelAndView modelAndView = new ModelAndView("booking/employee");
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView showAllUsersBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        ModelAndView modelAndView = new ModelAndView("booking/user");
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }




//    @GetMapping("/employee")
//    public String employeeBooking() {
//        return "/booking/employee";
//    }

    @GetMapping("/add")
    public String addBookingForm(@ModelAttribute("messageType") String messageType,
                                 @ModelAttribute("message") String message, Model model) {

        List<Car> cars = carService.getAllCars().stream()
                .filter(Car::isActive).collect(Collectors.toList());
        model.addAttribute("cars", cars);

        List<Branch> branches = branchService.getAllBranches().stream()
                .filter(Branch::isActive).collect(Collectors.toList());
        model.addAttribute("branches", branches);

        return "booking/add";
    }



   /* @PostMapping("/add")
    public String addBooking(Booking booking, Model model) throws Exception {
        boolean createResult = bookingService.createBooking(booking);
    public String addBookingForm() {
        return "booking/add";
    }
    @PostMapping("/add")
    public String addBooking(@Valid Booking booking, Model model) throws Exception {
        boolean createResult = bookingService.addBooking(booking);
        if (createResult) {
            model.addAttribute("message", "Booking has been successfully created.");
            model.addAttribute("messageType", "success");
            return "booking/list";
        }
        model.addAttribute("booking", booking);
        model.addAttribute("message", "Error in creating a booking.");
        model.addAttribute("messageType", "error");
        return "redirect:/booking";
    }*/



    @PostMapping("/add")
    public String createBooking(@ModelAttribute("booking") @Valid Booking booking,
                                BindingResult result, RedirectAttributes redirectAttributes) throws Exception {

        if (!searchService.searchForBooking(booking.getDateFrom(), booking.getDateTo(), booking.getCar())){
            redirectAttributes.addFlashAttribute("message", "Dates already booked.");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "booking/add";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("booking", booking);
            redirectAttributes.addFlashAttribute("message", "Error in creating a booking!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "booking/add";
        }

        bookingService.createBooking(booking);
        redirectAttributes.addFlashAttribute("message", "Booking has been successfully created.");
        redirectAttributes.addFlashAttribute("messageType", "success");
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

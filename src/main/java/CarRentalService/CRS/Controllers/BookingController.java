package CarRentalService.CRS.Controllers;

import CarRentalService.CRS.Models.Booking;
import CarRentalService.CRS.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String showAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "show-all-bookings";
    }

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
        } else {
            model.addAttribute("booking", booking);
            model.addAttribute("message", "Error in creating a booking.");
            model.addAttribute("messageType", "error");
            return addBookingForm(model);
        }
    }

    @GetMapping("/update")
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
        } else {
            model.addAttribute("booking", booking);
            model.addAttribute("message", "Error in updating booking");
            model.addAttribute("messageType", "error");
            return updateBookingForm(model);
        }
    }

    @GetMapping("/delete/{id}")
    public String cancelBooking(@PathVariable("id") Long bookingId, Model model) throws Exception {
        boolean deleteResult = bookingService.cancelBooking(bookingId);

        if (deleteResult) {
            model.addAttribute("message", "Booking has been successfully cancelled");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in cancelling booking.");
            model.addAttribute("messageType", "error");
        }

        return showAllBookings(model);
    }

    @GetMapping("/restore/{id}")
    public String restoreBooking(@PathVariable("id") Long bookingId, Model model) throws Exception {
        boolean restoreResult = bookingService.restoreBooking(bookingId);

        if (restoreResult) {
            model.addAttribute("message", "Booking has been successfully restored.");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in restoring booking.");
            model.addAttribute("messageType", "error");
        }
        return showAllBookings(model);
    }

}

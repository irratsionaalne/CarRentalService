package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Booking;
import CarRentalService.CRS.Repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public boolean createBooking(Booking booking) {
        if (booking == null) {
            return false;
        }

        booking.setActive(true);
        bookingRepo.save(booking);
        return true;
    }

    @Override
    public boolean updateBooking(Booking booking) {
        if (booking == null || !bookingRepo.existsById(booking.getId())) {
            return false;
        }

        bookingRepo.saveAndFlush(booking);
        return true;
    }

    @Override
    public boolean cancelBooking(Long bookingId) {
        Booking booking = getById(bookingId);
        if (booking == null) {
            return false;
        }
        booking.setActive(false);
        updateBooking(booking);
        return true;
    }

    @Override
    public boolean restoreBooking(Long bookingId) {
        Booking booking = getById(bookingId);
        if (booking == null) {
            return false;
        }

        booking.setActive(true);
        updateBooking(booking);
        return true;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public Booking getById(Long bookingId) {
        return bookingRepo.getOne(bookingId);
    }
}

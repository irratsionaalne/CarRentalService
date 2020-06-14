package CarRentalService.CRS.services;

import CarRentalService.CRS.models.Booking;
import CarRentalService.CRS.repositories.BookingRepo;
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
        return false;
    }

    @Override
    public boolean cancelBooking(Long bookingId) {
        return false;
    }

    @Override
    public boolean restoreBooking(Long bookingId) {
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        return null;
    }

    @Override
    public Booking getById(Long bookingId) {
        return null;
    }
}

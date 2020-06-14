package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    @Override
    public boolean createBooking() {
        return false;
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

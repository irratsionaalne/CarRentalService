package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Booking;

import java.util.List;

public interface BookingService {

    boolean createBooking();

    boolean updateBooking(Booking booking);

    boolean cancelBooking(Long bookingId);

    boolean restoreBooking(Long bookingId);

    List<Booking> getAllBookings();

    Booking getById(Long bookingId);
    
}

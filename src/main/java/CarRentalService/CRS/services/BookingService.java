package CarRentalService.CRS.services;

import CarRentalService.CRS.models.Booking;

import java.util.List;

public interface BookingService {

    boolean createBooking(Booking booking);

    boolean updateBooking(Booking booking);

    boolean cancelBooking(Long bookingId);

    boolean restoreBooking(Long bookingId);

    List<Booking> getAllBookings();

    Booking getById(Long bookingId);
    
}

package CarRentalService.CRS.Services;

import CarRentalService.CRS.Models.Booking;

import java.util.List;

public interface BookingService {

    boolean createBooking(Booking booking) throws Exception;

    boolean updateBooking(Booking booking);

    boolean cancelBooking(Long bookingId) throws Exception;

/*
    boolean restoreBooking(Long bookingId) throws Exception;
*/

    List<Booking> getAllBookings();

    Booking getById(Long bookingId);
    
}

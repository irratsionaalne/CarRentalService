package com.crs.services;

import com.crs.models.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    boolean addBooking(Booking booking) throws Exception;

    boolean updateBooking(Booking booking) throws Exception;

    boolean cancelBooking(UUID bookingId) throws Exception;
/*
    boolean restoreBooking(UUID bookingId) throws Exception;
*/

    List<Booking> getAllBookings();

    Booking getById(UUID bookingId);
    
}

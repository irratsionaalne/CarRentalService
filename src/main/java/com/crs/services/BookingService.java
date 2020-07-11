package com.crs.services;

import com.crs.dto.BookingDto;
import com.crs.models.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingDto bookingDto) throws Exception;

    boolean createBooking(Booking booking) throws Exception;

    boolean updateBooking(Booking booking) throws Exception;

    boolean cancelBooking(Long bookingId) throws Exception;

/*
    boolean restoreBooking(Long bookingId) throws Exception;
*/

    List<Booking> getAllBookings();

    Booking getById(Long bookingId);
    
}

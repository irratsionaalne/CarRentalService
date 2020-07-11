package com.crs.services;

import com.crs.dto.BookingDto;
import com.crs.models.Booking;
import com.crs.models.Car;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking createBooking(BookingDto bookingDto) throws Exception;

    boolean createBooking(Booking booking) throws Exception;

    boolean updateBooking(Booking booking) throws Exception;

    boolean cancelBooking(UUID bookingId) throws Exception;
/*
    boolean restoreBooking(UUID bookingId) throws Exception;
*/

    List<Booking> getAllBookings();

    Booking getById(UUID bookingId);
    
}

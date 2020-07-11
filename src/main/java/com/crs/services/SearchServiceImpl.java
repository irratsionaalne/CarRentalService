package com.crs.services;

import com.crs.models.Booking;
import com.crs.models.Car;
import com.crs.models.CarStatus;
import com.crs.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private BookingRepo bookingRepo;
    @Override
    public boolean searchForBooking(LocalDateTime dayFrom, LocalDateTime dateTo, Car car) throws Exception {

        if (car.getStatus()==CarStatus.NOT_AVAILABLE) return false;

        List<Booking> bookings=bookingRepo.findAll();
        List<Booking> carBookings=null;

        for (Booking booking:bookings){
            if (booking.getCar().equals(car)){
                carBookings.add(booking);
            }
        }

        for (Booking booking:carBookings) {
            if (dayFrom.isEqual(booking.getDateFrom()) | dayFrom.isEqual(booking.getDateTo()) |
                    dateTo.isEqual(booking.getDateFrom()) | dateTo.isEqual(booking.getDateTo()))
                return false;
        }

        for (Booking booking:carBookings) {
            if (dayFrom.isAfter(booking.getDateFrom()) & dayFrom.isBefore(booking.getDateTo()) |
                    dateTo.isBefore(booking.getDateFrom()) & dateTo.isAfter(booking.getDateTo()))
                return false;
        }

        return true;
    }

}


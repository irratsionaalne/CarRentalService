package com.crs.services;

import com.crs.models.Booking;
import com.crs.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public boolean createBooking(Booking booking) throws Exception {
        booking.setStatus("UPCOMING");
        booking.setDateOfBooking(LocalDate.now());
        bookingRepo.save(booking);
        return true;
    }

    @Override
    public boolean updateBooking(Booking booking) throws Exception {
        if (booking == null || !bookingRepo.existsById(booking.getId())) {
            throw new Exception("Invalid updating of Booking");
        }
        bookingRepo.saveAndFlush(booking);
        return true;
    }

    @Override
    public boolean cancelBooking(UUID bookingId) throws Exception {
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public Booking getById(UUID bookingId) {
        return bookingRepo.getOne(bookingId);
    }

}

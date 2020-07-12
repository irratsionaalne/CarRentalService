package com.crs.services;

import com.crs.dto.BookingDto;
import com.crs.models.Booking;
import com.crs.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public Booking createBooking(BookingDto bookingDto) throws Exception {
        Booking booking = new Booking();
        booking.setDateOfBooking(bookingDto.getDateOfBooking());
        booking.setUser(bookingDto.getUser());
        booking.setCar(bookingDto.getCar());
        booking.setDateFrom(bookingDto.getDateFrom());
        booking.setDateTo(bookingDto.getDateTo());
        booking.setStatus(bookingDto.getStatus());
        return bookingRepo.save(booking);
    }

    @Override
    public boolean createBooking(Booking booking) throws Exception {
        return false;
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

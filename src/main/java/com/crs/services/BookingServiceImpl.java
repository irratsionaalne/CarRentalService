package com.crs.services;

import com.crs.dto.BookingDto;
import com.crs.models.Booking;
import com.crs.repositories.BookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;

    @Override
    public Booking createBooking(BookingDto bookingDto) throws Exception {
        Booking booking = new Booking();
        booking.setDateOfBooking(bookingDto.getDateOfBooking());
        booking.setCustomer(bookingDto.getCustomer());
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
    public boolean cancelBooking(Long bookingId) throws Exception {
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public Booking getById(Long bookingId) {
        return bookingRepo.getOne(bookingId);
    }
}

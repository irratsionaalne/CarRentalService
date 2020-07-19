package com.crs.services;

import com.crs.models.Booking;
import com.crs.models.BookingStatus;
import com.crs.models.Car;
import com.crs.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private CarService carService;

    @Override
    public boolean createBooking(Booking booking) throws Exception {
        Car car = carService.getById(booking.getCar().getId());
        booking.setStatus(BookingStatus.UPCOMING);
        booking.setDateOfBooking(LocalDate.now());
        booking.setTotalPrice(booking.getTotalPrice(car.getPricePerDay()));
        System.out.println(booking.getTotalPrice());
        bookingRepo.save(booking);
        return true;
    }

    @Override
    public boolean modifyBooking(UUID id,Booking booking) throws Exception {
        Optional<Booking> optionalBooking = bookingRepo.findById(id);
        if (!optionalBooking.isPresent()) {
            throw new Exception("Invalid updating of Booking");
        }
        Booking bookingToUpdate = optionalBooking.get();
        if (!bookingToUpdate.getDateFrom().isEqual(booking.getDateFrom())){
          bookingToUpdate.setDateFrom(booking.getDateFrom());
        }
        if (bookingToUpdate.getCar() != booking.getCar()){
            bookingToUpdate.setCar(booking.getCar());
        }
        if (!bookingToUpdate.getDateTo().isEqual(booking.getDateTo())){
            bookingToUpdate.setDateTo(booking.getDateTo());
        }
        if (bookingToUpdate.getStatus() != booking.getStatus()){
            bookingToUpdate.setStatus(booking.getStatus());
        }
        if (bookingToUpdate.getCarRentalComment() == null || !bookingToUpdate.getCarRentalComment().equals(booking.getCarRentalComment())){
            bookingToUpdate.setCarRentalComment(booking.getCarRentalComment());
        }
        if (bookingToUpdate.getCarReturnComment() == null || !bookingToUpdate.getCarReturnComment().equals(booking.getCarReturnComment())){
            bookingToUpdate.setCarReturnComment(booking.getCarReturnComment());
        }
        if (bookingToUpdate.getExtraPayment() != booking.getExtraPayment()){
            bookingToUpdate.setExtraPayment(booking.getExtraPayment());
        }

        bookingRepo.save(bookingToUpdate);
        return true;
    }

    @Override
    public boolean cancelBooking(UUID bookingId) {
        Booking booking = bookingRepo.getOne(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.saveAndFlush(booking);
        return true;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public List<Booking> getAllBookingsByBranch() {
        return null;
    }

    @Override
    public List<Booking> getAllBookingsByUser() {
        return null;
    }

    @Override
    public Booking getById(UUID bookingId) {
        return bookingRepo.getOne(bookingId);
    }

}

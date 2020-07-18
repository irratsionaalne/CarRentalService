package com.crs.services;

import com.crs.models.Car;
import com.crs.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public boolean searchForBooking(LocalDate dayFrom, LocalDate dateTo, Car car) {
        return bookingRepo.findBookingByDateAndCar(dayFrom, dateTo, car).size() < 1;
    }

}



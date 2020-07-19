package com.crs.services;

import com.crs.models.Car;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface SearchService {

    boolean searchForBooking(LocalDate dateFrom, LocalDate dateTo, Car car ) throws Exception;
    Double revenue();

}

package com.crs.services;

import com.crs.models.Car;

import java.time.LocalDateTime;


public interface SearchService {

    boolean searchForBooking(LocalDateTime dateFrom, LocalDateTime dateTo, Car car ) throws Exception;

}

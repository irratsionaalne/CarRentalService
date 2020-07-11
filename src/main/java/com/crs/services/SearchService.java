package com.crs.services;

import com.crs.models.Car;


public interface SearchService {

    boolean searchForBooking(String fromDate, String toDate, Car car, String city) throws Exception;

}

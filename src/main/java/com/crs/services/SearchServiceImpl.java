package com.crs.services;

import com.crs.models.Car;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{
    @Override
    public boolean searchForBooking(String fromDate, String todate, Car car, String city) throws Exception {
        return false;
    }
}

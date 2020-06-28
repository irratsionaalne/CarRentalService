package com.crs.services;

import com.crs.models.CarRental;

import java.util.List;

public interface CarRentalService {

    boolean createCarRental(CarRental carRental) throws Exception;

    boolean updateCarRental(CarRental carRental) throws Exception;

    boolean setCarRentalStatus(Long carRentalId) throws Exception;

    CarRental getById(Long carRentalId);

    boolean addComment(Long carRentalId,String carRentalComment);

    List<CarRental> getAllCarRentals();
}

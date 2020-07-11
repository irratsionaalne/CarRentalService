package com.crs.services;

import com.crs.models.CarRental;

import java.util.List;
import java.util.UUID;

public interface CarRentalService {

    boolean createCarRental(CarRental carRental) throws Exception;

    boolean updateCarRental(CarRental carRental) throws Exception;

    boolean setCarRentalStatus(UUID carRentalId) throws Exception;

    CarRental getById(UUID carRentalId);

    boolean addComment(UUID carRentalId,String carRentalComment);

    List<CarRental> getAllCarRentals();
}

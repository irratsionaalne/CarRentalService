package com.crs.services;

import com.crs.models.CarReturn;

import java.util.List;
import java.util.UUID;

public interface CarReturnService {

    boolean createCarReturn(CarReturn carReturn) throws Exception;

    boolean updateCarReturn(CarReturn carReturn) throws Exception;

    boolean setCarReturnStatus(UUID carReturnId) throws Exception;

    CarReturn getById(UUID carReturnId);

    boolean addComment(UUID carReturnId,String carRentalComment);

    boolean addAdditionalPayment(UUID carReturnId,String carAdditionalPayment);

    List<CarReturn> getAllCarReturns();
}

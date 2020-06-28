package com.crs.services;

import com.crs.models.CarReturn;

import java.util.List;

public interface CarReturnService {

    boolean createCarReturn(CarReturn carReturn) throws Exception;

    boolean updateCarReturn(CarReturn carReturn) throws Exception;

    boolean setCarReturnStatus(Long carReturnId) throws Exception;

    CarReturn getById(Long carReturnId);

    boolean addComment(Long carReturnId,String carRentalComment);

    boolean addAdditionalPayment(Long carReturnId,String carAdditionalPayment);

    List<CarReturn> getAllCarReturns();
}

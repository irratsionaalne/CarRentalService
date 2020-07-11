package com.crs.dto;

import com.crs.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class BookingDto extends Booking {

    @NotEmpty
    private Date dateOfBooking;

    @NotEmpty
    private Customer customer;

    @NotEmpty
    private Car car;

    @NotEmpty
    private LocalDateTime dateFrom;

    @NotEmpty
    private LocalDateTime dateTo;

    @NotEmpty
    private CarRental carRental;

    @NotEmpty
    private CarReturn carReturn;

    @NotEmpty
    private String totalPrice;

    @NotEmpty
    private boolean  status;


}

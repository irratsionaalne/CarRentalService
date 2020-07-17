package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotEmpty
    private LocalDate dateOfBooking=LocalDate.now();
    @OneToOne
    @NotEmpty
    private User user;
    @OneToOne
    @NotEmpty
    private Car car;
//    @NotEmpty
//    private CarRental carRental;
//
//    @NotEmpty
//    private CarReturn carReturn;
    @NotEmpty
    private LocalDateTime dateFrom;
    @NotEmpty
    private LocalDateTime dateTo;
    private String carRentalComment;
    private String carReturnComment;
    private String extraPayment;
    private String totalPrice;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String fullDateFormat() {
        return dateOfBooking.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }


    }

package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty
    private LocalDate dateOfBooking;
    @NotEmpty
    private String username;
    @OneToOne
    @NotEmpty
    private Car car;
    //    @NotEmpty
//    private CarRental carRental;
//
//    @NotEmpty
//    private CarReturn carReturn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty
    private LocalDate dateTo;
    private String carRentalComment;
    private String carReturnComment;
    private String extraPayment;
    private double totalPrice;
    private String status;
    @OneToOne(fetch = FetchType.EAGER)
    private Branch rentalBranch;
    @OneToOne(fetch = FetchType.EAGER)
    private Branch returnBranch;

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
        return LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
    public double getTotalPrice(double pricePerDay){
        Period p=Period.between(this.dateFrom,this.dateTo);
        return pricePerDay * (p.getDays() + 1);
    }



}

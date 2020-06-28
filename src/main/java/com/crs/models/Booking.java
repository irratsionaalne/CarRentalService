package com.crs.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOfBooking;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Car car;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    @OneToOne
    private CarRental carRental;
    @OneToOne
    private CarReturn carReturn;
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
}

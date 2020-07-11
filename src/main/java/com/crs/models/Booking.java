package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;
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

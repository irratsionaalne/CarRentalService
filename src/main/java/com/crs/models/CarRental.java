package com.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private LocalDateTime rentalDate;
    @OneToOne
    private Branch rentalBranch;
    @OneToOne
    private Booking booking;
    private String comment;
    private boolean isActive;
}

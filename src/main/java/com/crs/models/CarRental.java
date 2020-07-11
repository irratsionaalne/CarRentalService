package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class CarRental {

    @Id
    @GeneratedValue
    private UUID id;
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

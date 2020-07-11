package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class CarRental {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

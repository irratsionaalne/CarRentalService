package com.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CarReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private LocalDateTime dateOfReturn;
    @OneToOne
    private Branch returnBranch;
    @OneToOne
    private Booking booking;
    private String additionalPayment;
    private String comment;
    private boolean isActive;
}

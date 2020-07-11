package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class CarReturn {

    @Id
    @GeneratedValue
    private UUID id;
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

package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(value = EnumType.STRING)
    private EmployeeRole role;
    private String branch;
    @OneToMany
    private List<Booking> bookings;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}

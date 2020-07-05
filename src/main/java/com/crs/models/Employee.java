package com.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "employee")
@Table(name = "employee")
@Data
public class Employee {

    @Id
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private EmployeeRole role;
    private String branch;
    @OneToMany
    private Set<Booking> bookings;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

}

package com.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "employee")
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobPosition;
    @ManyToOne
    private Branch branch;
    @OneToMany
    private Set<Booking> bookings;
    private boolean isActive = true;
    private String password;

}

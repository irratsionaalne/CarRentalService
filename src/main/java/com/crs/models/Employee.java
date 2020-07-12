package com.crs.models;



import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String password;
    //@Email
    @NotEmpty
    private String email;
    @AssertTrue
    private boolean terms;
    @NotEmpty
    private String role;
    //@NotNull
    @NotEmpty
    private String branch;
    @OneToMany
    private List<Booking> bookings;
    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private User user;

}

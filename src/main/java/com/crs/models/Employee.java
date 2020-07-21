package com.crs.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToOne(fetch = FetchType.EAGER)
    @NotBlank
    private Branch branch;
    @OneToMany
    private List<Booking> bookings;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @Email
    @NotEmpty
    private String email;
    @AssertTrue
    private Boolean terms;

}

package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;
    private String address;
    private LocalDate dob;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

}

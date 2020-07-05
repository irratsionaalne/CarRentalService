package com.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "customer")
@Table(name = "customer")
@Data
public class Customer {

    @Id
    private Long id;
    private String address;
    private LocalDate dob;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

}

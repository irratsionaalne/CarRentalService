package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue
    private UUID id;
    private String streetAddress;
    private String city;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Car> cars;
    private boolean isActive;
}

package com.crs.models;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Office {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String address;
    private String internetDomain;
    private String owner;
    @OneToMany
    private List<Branch> branches;
    private String logotype;
    private boolean isActive;
}

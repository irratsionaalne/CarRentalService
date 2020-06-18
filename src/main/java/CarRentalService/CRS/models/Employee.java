package CarRentalService.CRS.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String jobPosition;
    @ManyToOne
    private Branch branch;
    @OneToMany
    private List<Booking> bookings;
    private boolean isActive;
}

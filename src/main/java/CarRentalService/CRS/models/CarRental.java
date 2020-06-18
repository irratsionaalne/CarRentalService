package CarRentalService.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private Date rentalDate;
    @OneToOne
    private Branch rentalBranch;
    @OneToOne
    private Booking booking;
    private String comment;
    private boolean isActive;
}

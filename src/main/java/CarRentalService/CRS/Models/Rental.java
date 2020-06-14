package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private String rentalDate;
    @OneToOne
    private Booking booking;
    private String comment;
}

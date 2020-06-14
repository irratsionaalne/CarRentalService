package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dateOfBooking;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Car car;
    private String dateFrom;
    private String dateTo;
    @OneToOne
    private Branch rentalBranch;
    @OneToOne
    private Branch returnBranch;
    private String amount;
}

package CarRentalService.CRS.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOfBooking;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Car car;
    private Date dateFrom;
    private Date dateTo;
    @OneToOne
    private CarRental carRental;
    @OneToOne
    private CarReturn carReturn;
    private String totalPrice;
    private String status;
}

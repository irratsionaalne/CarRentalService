package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private String dateOfReturn;
    @OneToOne
    private Booking booking;
    private String additionalPayment;
    private String comment;
}

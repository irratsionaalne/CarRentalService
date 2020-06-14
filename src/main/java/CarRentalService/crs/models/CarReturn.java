package CarRentalService.crs.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CarReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Employee employee;
    private String dateOfReturn;
    @OneToOne
    private Branch returnBranch;
    @OneToOne
    private Booking booking;
    private String additionalPayment;
    private String comment;
    private boolean isActive;
}

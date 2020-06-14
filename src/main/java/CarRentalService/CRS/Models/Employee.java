package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;

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
}

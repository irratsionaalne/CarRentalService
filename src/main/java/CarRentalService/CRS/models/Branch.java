package CarRentalService.CRS.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String streetAddress;
    private String city;
    @OneToMany
    private List<CarRentalService.crs.models.Employee> employees;
    @OneToMany
    private List<CarRentalService.crs.models.Car> cars;
    private boolean isActive;
}

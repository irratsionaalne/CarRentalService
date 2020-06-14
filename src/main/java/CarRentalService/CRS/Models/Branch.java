package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Car> cars;
}

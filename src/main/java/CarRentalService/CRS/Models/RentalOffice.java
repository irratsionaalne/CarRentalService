package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RentalOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String internetDomain;
    private String owner;
    @OneToMany
    private List<Branch> branches;
    private String logotype;
}

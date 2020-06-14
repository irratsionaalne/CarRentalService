package CarRentalService.crs.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Office {

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
    private boolean isActive;
}

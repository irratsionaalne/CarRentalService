package CarRentalService.CRS.Models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private String bodyType;
    private String year;
    private String color;
    private String mileage;
    private String status;
    private String pricePerDay;
    private boolean isActive;
}

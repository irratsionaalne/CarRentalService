package com.crs.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class BranchCreationDto {

    @NotEmpty
    private String streetAddress;

    @NotEmpty
    private String city;


}

package com.crs.dto;

import com.crs.constraint.FieldMatch;
import com.crs.models.EmployeeRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
@Getter
@Setter
@ToString
public class EmployeeRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String branch;

    @NotEmpty
    private EmployeeRole role;

    @Email
    @NotEmpty
    private String email;

    @AssertTrue
    private Boolean terms;
}

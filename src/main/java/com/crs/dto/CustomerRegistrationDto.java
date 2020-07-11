package com.crs.dto;

import com.crs.constraint.FieldMatch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
@Getter
@Setter
public class CustomerRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String dob;

    @AssertTrue
    private Boolean terms;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;


}

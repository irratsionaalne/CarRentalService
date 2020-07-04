package com.crs.controllers.dto;

import com.crs.constraint.FieldMatch;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

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

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String address;

    @NotEmpty
    private String dob;

    @Email
    @NotEmpty
    private String email;

    @AssertTrue
    private Boolean terms;


}

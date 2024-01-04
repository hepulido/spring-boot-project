package com.example.connectionApi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class UserSignUpDTO {
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, message = "First name must have at least 2 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, message = "Last name must have at least 2 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    private String typeOfUser;
}

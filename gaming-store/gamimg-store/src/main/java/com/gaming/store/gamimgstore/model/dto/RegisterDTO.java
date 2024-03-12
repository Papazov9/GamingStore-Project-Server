package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty(message = "Username can't be empty.")
    @Size(min = 2,max = 20,message = "Must be between 2 and 20 symbols.")
    private String username;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 5,message = "Password must be at least 5 symbols.")
    private String password;

    @NotEmpty(message = "Password is required.")
    @Size(min = 5,message = "Password must be at least 5 symbols.")
    private String confirmPassword;

    @Pattern(regexp = "[0-9]\\d{1,20}",message = "Phone is invalid.")
    private String phone;

    @Min(value = 10,message = "Minimum age is 10.")
    @Max(value = 99,message = "Maximum age is 99.")
    private Integer age;

    @NotEmpty(message = "First Name is required.")
    @Size(min = 3,max = 20,message = "First Name must be between 2 nad 20 symbols.")
    private String firstName;

    @NotEmpty(message = "Last Name is required.")
    @Size(min = 3,max = 20,message = "Last Name must be between 2 nad 20 symbols.")
    private String lastName;
}

package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotEmpty(message = "Username can't be empty.")
    @Size(min = 2,max = 20,message = "Must be between 2 and 20 symbols.")
    private String username;

    @NotEmpty(message = "Password is required.")
    @Size(min = 5,message = "Password must be at least 5 symbols.")
    private String password;
}

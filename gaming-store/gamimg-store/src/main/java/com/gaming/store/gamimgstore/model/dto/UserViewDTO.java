package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserViewDTO {

    private String username;

    private String email;

    private String phone;

    private Integer age;

    private String firstName;

    private String lastName;

    private String createdAt;

    private String modifiedAt;
}

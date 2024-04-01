package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequest {

    @NotEmpty
    @NotNull
    private String username;

    @NotNull
    @NotEmpty
    private String productId;
}

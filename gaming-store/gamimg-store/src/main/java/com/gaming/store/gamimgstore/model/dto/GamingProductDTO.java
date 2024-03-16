package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GamingProductDTO {

    @NotEmpty
    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isOnSale;

    private String imageUrl;
}

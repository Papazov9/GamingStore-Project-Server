package com.gaming.store.gamimgstore.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GamingProductView {

    private  String id;
    @NotEmpty
    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isOnSale;

    private String imageUrl;
}

package com.gaming.store.gamimgstore.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gaming_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GamingProduct extends BaseEntity{

    @Column(nullable = false)
    private  String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private boolean isOnSale;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime lastModified;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String imageUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CategoryEntity> categories;

}

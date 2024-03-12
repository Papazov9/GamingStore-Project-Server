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
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean isActive;
    @Column
    private boolean isOnSale = false;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime lastModified;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CategoryEntity> categories;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ImageEntity.class, mappedBy = "product")
    private List<ImageEntity> images = new ArrayList<>();
}

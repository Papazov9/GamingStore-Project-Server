package com.gaming.store.gamimgstore.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageEntity extends BaseEntity{

    @Column(nullable = false)
    private String publicId;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private GamingProduct product;
}

package com.gaming.store.gamimgstore.repo;

import com.gaming.store.gamimgstore.model.entity.GamingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<GamingProduct, String> {
    Optional<GamingProduct> findByName(String name);
}

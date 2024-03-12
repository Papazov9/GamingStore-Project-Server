package com.gaming.store.gamimgstore.repo;

import com.gaming.store.gamimgstore.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {
}

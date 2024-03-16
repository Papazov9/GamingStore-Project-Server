package com.gaming.store.gamimgstore.service;

import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.GamingProductDTO;
import com.gaming.store.gamimgstore.model.entity.GamingProduct;
import com.gaming.store.gamimgstore.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamingProductService {

    private final ProductRepository productRepository;
    public List<GamingProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private GamingProductDTO mapToDto(GamingProduct p) {
        return new GamingProductDTO(p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.isOnSale(),
                p.getImageUrl());
    }

    public GamingProductDTO addNewProduct(GamingProductDTO productDTO) {
        Optional<GamingProduct> byName = productRepository.findByName(productDTO.getName());
        if (byName.isPresent()) {
            throw new AppException("Product with name: " + productDTO.getName() + " already exists!", HttpStatus.BAD_REQUEST);
        }

        GamingProduct productToAdd = new GamingProduct(productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getIsOnSale(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                productDTO.getImageUrl(),
                List.of());

        productRepository.saveAndFlush(productToAdd);
        return productDTO;
    }
}

package com.gaming.store.gamimgstore.service;

import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.GamingProductDTO;
import com.gaming.store.gamimgstore.model.dto.GamingProductView;
import com.gaming.store.gamimgstore.model.entity.GamingProduct;
import com.gaming.store.gamimgstore.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamingProductService {

    private final ProductRepository productRepository;
    public List<GamingProductView> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private GamingProductView mapToDto(GamingProduct p) {
        return new GamingProductView(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.isOnSale(),
                p.getImageUrl()
        );
    }

    public GamingProductDTO addNewProduct(GamingProductDTO productDTO) {
        Optional<GamingProduct> byName = productRepository.findByName(productDTO.getName());
        if (byName.isPresent()) {
            throw new AppException("Product with name: " + productDTO.getName() + " already exists!", HttpStatus.BAD_REQUEST);
        }

        if (productDTO.getImageUrl() == null) {
            throw new AppException("Image file is required!", HttpStatus.BAD_REQUEST);
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

    public GamingProductView findById(String id) {
        Optional<GamingProduct> productById = productRepository.findById(id);

        if (productById.isEmpty()) {
            throw new AppException("Product with this id does not exists!", HttpStatus.BAD_REQUEST);
        }

        return mapToDto(productById.get());
    }

    public GamingProductDTO editProduct(GamingProductDTO productDTO) {

        GamingProduct product = productRepository
                .findById(productDTO.getId())
                .orElseThrow(() ->
                        new AppException("Product with this id does not exists!", HttpStatus.BAD_REQUEST));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl() != null ? productDTO.getImageUrl() :product.getImageUrl());
        product.setLastModified(LocalDateTime.now());
        product.setOnSale(productDTO.getIsOnSale());

        productRepository.saveAndFlush(product);

        return productDTO;
    }

    @Transactional
    public GamingProductView deleteProduct(String id) {
        GamingProduct gamingProduct = productRepository.findById(id).orElseThrow(() -> new AppException("Invalid id!", HttpStatus.BAD_REQUEST));
        GamingProductView gamingProductView = mapToDto(gamingProduct);
        productRepository.delete(gamingProduct);
        return gamingProductView;
    }
}

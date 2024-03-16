package com.gaming.store.gamimgstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.GamingProductDTO;
import com.gaming.store.gamimgstore.service.GamingProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductController {

    private final GamingProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<GamingProductDTO>> allProducts() {
       List<GamingProductDTO> productDTOS = this.productService.getAllProducts();

       return ResponseEntity.ok(productDTOS);
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<GamingProductDTO> addNewProduct(@Valid @RequestBody GamingProductDTO productDTO) {

        GamingProductDTO gamingProductDTO = productService.addNewProduct(productDTO);

        return ResponseEntity.ok(gamingProductDTO);
    }
}

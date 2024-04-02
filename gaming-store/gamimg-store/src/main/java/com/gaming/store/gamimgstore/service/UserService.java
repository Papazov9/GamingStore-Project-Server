package com.gaming.store.gamimgstore.service;

import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.CartRequest;
import com.gaming.store.gamimgstore.model.dto.GamingProductView;
import com.gaming.store.gamimgstore.model.dto.UserViewDTO;
import com.gaming.store.gamimgstore.model.entity.GamingProduct;
import com.gaming.store.gamimgstore.model.entity.UserEntity;
import com.gaming.store.gamimgstore.repo.ProductRepository;
import com.gaming.store.gamimgstore.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException("User not found!", HttpStatus.NOT_FOUND));
    }

    public List<GamingProductView> getCartProducts(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new AppException("Invalid username", HttpStatus.BAD_REQUEST));

        return user.getGamingProducts()
                .stream()
                .map(p ->
                        new GamingProductView(
                                p.getId(),
                                p.getName(),
                                p.getDescription(),
                                p.getPrice(),
                                p.isOnSale(),
                                p.getImageUrl()))
                .collect(Collectors.toList());
    }

    public GamingProductView addProductToCart(CartRequest cartRequest) {
        GamingProduct gamingProduct = productRepository.findById(cartRequest.getProductId()).orElseThrow(() -> new AppException("Invalid product id!", HttpStatus.BAD_REQUEST));
        UserEntity user = userRepository.findByUsername(cartRequest.getUsername()).orElseThrow(() -> new AppException("Invalid user!", HttpStatus.BAD_REQUEST));

        List<GamingProduct> gamingProducts = user.getGamingProducts();
        gamingProducts.add(gamingProduct);
        user.setGamingProducts(gamingProducts);

        userRepository.saveAndFlush(user);

        return new GamingProductView(
                gamingProduct.getId(),
                gamingProduct.getName(),
                gamingProduct.getDescription(),
                gamingProduct.getPrice(),
                gamingProduct.isOnSale(),
                gamingProduct.getImageUrl());
    }

    public UserViewDTO getUserInfoByUsername(String username) {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException("Invalid username!", HttpStatus.BAD_REQUEST));

        return UserViewDTO
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .modifiedAt(user.getLastModified().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }
}

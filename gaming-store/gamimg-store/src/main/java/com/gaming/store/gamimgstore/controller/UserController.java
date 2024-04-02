package com.gaming.store.gamimgstore.controller;

import com.gaming.store.gamimgstore.model.dto.CartRequest;
import com.gaming.store.gamimgstore.model.dto.GamingProductView;
import com.gaming.store.gamimgstore.model.dto.UserViewDTO;
import com.gaming.store.gamimgstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/cartProducts/{username}")
    public ResponseEntity<List<GamingProductView>> cartProducts(@PathVariable String username) {
        List<GamingProductView> cartProducts = userService.getCartProducts(username);
        return ResponseEntity.ok(cartProducts);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<GamingProductView> addToCart (@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(userService.addProductToCart(cartRequest));
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserViewDTO> getDetailedInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserInfoByUsername(username));
    }
}

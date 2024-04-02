package com.gaming.store.gamimgstore.controller;

import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.LoginDTO;
import com.gaming.store.gamimgstore.model.dto.LoginResponse;
import com.gaming.store.gamimgstore.model.dto.RegisterDTO;
import com.gaming.store.gamimgstore.model.entity.UserEntity;
import com.gaming.store.gamimgstore.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@Valid @RequestBody RegisterDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException("All fields are required.", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = authenticationService.register(userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException("All fields are required.", HttpStatus.BAD_REQUEST);
        }

        LoginResponse userLogin = authenticationService.login(loginDTO);

        if (userLogin.getUsername() == null) {
            throw new AppException("Invalid credentials!", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userLogin);
    }
}

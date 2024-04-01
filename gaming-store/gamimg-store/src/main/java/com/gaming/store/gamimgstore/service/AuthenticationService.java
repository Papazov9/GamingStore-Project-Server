package com.gaming.store.gamimgstore.service;

import com.gaming.store.gamimgstore.exceptions.AppException;
import com.gaming.store.gamimgstore.model.dto.LoginDTO;
import com.gaming.store.gamimgstore.model.dto.LoginResponse;
import com.gaming.store.gamimgstore.model.dto.RegisterDTO;
import com.gaming.store.gamimgstore.model.entity.UserEntity;
import com.gaming.store.gamimgstore.model.entity.UserRoleEntity;
import com.gaming.store.gamimgstore.model.enums.UserRoleEnum;
import com.gaming.store.gamimgstore.repo.UserRepository;
import com.gaming.store.gamimgstore.repo.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;
    public UserEntity register(RegisterDTO userDTO) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(userDTO.getUsername());
        if (byUsername.isPresent()) {
            throw new AppException("User with this username already exists!", HttpStatus.BAD_REQUEST);
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new AppException("Passwords do not match!", HttpStatus.BAD_REQUEST);
        }

        UserRoleEntity byUserRoleEnum = userRoleRepository.findByUserRoleEnum(UserRoleEnum.USER);

        UserEntity user = new UserEntity(
                userDTO.getUsername(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getPhone(),
                userDTO.getAge(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Set.of(byUserRoleEnum),
                List.of());

        return userRepository.saveAndFlush(user);
    }

    public LoginResponse login(LoginDTO loginDTO) {

        try {
            UserEntity user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
            Authentication auth;

            String jwtToken;
            if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDTO.getUsername(),
                                loginDTO.getPassword()));

                jwtToken = tokenService.generateJwt(auth);
                return new LoginResponse(user.getUsername(), user.getEmail(), jwtToken);
            } else {
                return new LoginResponse(null, null, "");
            }
        }catch (AuthenticationException e) {
            return new LoginResponse(null, null, "");
        }
    }
}

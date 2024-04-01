package com.gaming.store.gamimgstore.configuration;

import com.gaming.store.gamimgstore.model.entity.UserEntity;
import com.gaming.store.gamimgstore.model.entity.UserRoleEntity;
import com.gaming.store.gamimgstore.model.enums.UserRoleEnum;
import com.gaming.store.gamimgstore.repo.UserRepository;
import com.gaming.store.gamimgstore.repo.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER, "User role");
            UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN, "Admin role");

            userRoleRepository.saveAll(List.of(userRole, adminRole));
        }

        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = userRoleRepository.findByUserRoleEnum(UserRoleEnum.ADMIN);
            UserEntity adminUser = new UserEntity(
                    "admin",
                    "admin@admin.bg",
                    passwordEncoder.encode("123456"),
                    "123-123-123",
                    23,
                    "Admin",
                    "Admin",
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    Set.of(adminRole),
                    List.of());
            userRepository.saveAndFlush(adminUser);
        }
    }
}

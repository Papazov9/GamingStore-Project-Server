package com.gaming.store.gamimgstore.model.entity;

import com.gaming.store.gamimgstore.model.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleEntity extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, name = "user_role")
    private UserRoleEnum userRoleEnum;

    private String description;

    @Override
    public String getAuthority() {
        return userRoleEnum.name();
    }
}

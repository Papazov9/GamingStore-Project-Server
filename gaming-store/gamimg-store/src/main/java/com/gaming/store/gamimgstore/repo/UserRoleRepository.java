package com.gaming.store.gamimgstore.repo;

import com.gaming.store.gamimgstore.model.entity.UserRoleEntity;
import com.gaming.store.gamimgstore.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    UserRoleEntity findByUserRoleEnum(UserRoleEnum admin);
}

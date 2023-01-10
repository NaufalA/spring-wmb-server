package com.warungmakanbahari.warungmakanbahari.features.auth;

import com.warungmakanbahari.warungmakanbahari.features.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}

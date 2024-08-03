package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.User;
import com.zenthrex.core.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(RoleEnum roleEnum);
}
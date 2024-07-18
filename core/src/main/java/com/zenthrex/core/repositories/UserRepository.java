package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
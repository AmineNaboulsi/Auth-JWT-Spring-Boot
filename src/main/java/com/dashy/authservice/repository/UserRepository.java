package com.dashy.authservice.repository;

import com.dashy.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
<<<<<<< HEAD
=======
    Optional<User> findByUserName(String userName);
>>>>>>> feature/regisration
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}

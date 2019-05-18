package com.db.education.User.repository;

import com.db.education.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentNumber(String username);
    Boolean existsByStudentNumber(String username);
    Boolean existsByEmail(String email);
}

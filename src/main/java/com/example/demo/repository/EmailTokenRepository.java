package com.example.demo.repository;

import com.example.demo.entity.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EmailTokenRepository extends JpaRepository<EmailConfirmationToken, Integer> {
    EmailConfirmationToken findByToken(String token);
    EmailConfirmationToken findByTokenAndDateExpiredAfter(String token, Date dateBefore);
}

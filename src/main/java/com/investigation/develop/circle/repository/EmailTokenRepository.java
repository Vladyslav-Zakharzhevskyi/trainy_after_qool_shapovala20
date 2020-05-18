package com.investigation.develop.circle.repository;

import com.investigation.develop.circle.entity.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EmailTokenRepository extends JpaRepository<EmailConfirmationToken, Integer> {
    EmailConfirmationToken findByToken(String token);
    EmailConfirmationToken findByTokenAndDateExpiredAfter(String token, Date dateBefore);
}

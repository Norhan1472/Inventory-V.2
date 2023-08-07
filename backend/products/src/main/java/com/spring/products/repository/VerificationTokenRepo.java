package com.spring.products.repository;

import com.spring.products.entity.User;
import com.spring.products.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}

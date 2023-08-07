package com.spring.products.service;

import com.spring.products.entity.User;
import com.spring.products.entity.VerificationToken;

public interface VerificationTokenService {
    public VerificationToken findByToken(String token);
    public VerificationToken findByUser(User user);
    public void saveVerificationToken(String token, User user);
}

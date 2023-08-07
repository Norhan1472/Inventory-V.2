package com.spring.products.service.implementation;

import com.spring.products.Email.EmailSender;
import com.spring.products.dto.UserPrincipal;
import com.spring.products.entity.User;
import com.spring.products.entity.VerificationToken;
import com.spring.products.repository.UserRepo;
import com.spring.products.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {
    UserRepo userRepo;
    VerificationTokenService verificationTokenService;// = new VerificationTokenService();


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User checkUser = userRepo.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal(checkUser);

        return userPrincipal;
    }

    @Transactional
    public User signUp(User user) {
        return userRepo.save(user);
    }

}

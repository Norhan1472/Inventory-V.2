package com.spring.products.service.implementation;

import com.auth0.jwt.JWT;
import com.spring.products.dto.JwtLogin;
import com.spring.products.dto.JwtResponse;
import com.spring.products.dto.UserPrincipal;
import com.spring.products.entity.User;
import com.spring.products.repository.UserRepo;
import com.spring.products.dto.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
@Service
public class TokenService {
    private AuthenticationManager authenticationManager;
    private UserRepo userRepository;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager,UserRepo userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authResult) {

        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
        System.out.println(principal.getUsername());

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
        return token;
    }
    public JwtResponse login(JwtLogin jwtLogin) {
        System.out.println("Hello World");
        System.out.println(jwtLogin.getEmail());
        System.out.println(jwtLogin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtLogin.getEmail(),
                jwtLogin.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = generateToken(authenticate);
        User user = userRepository.findByEmail(jwtLogin.getEmail());
        System.out.println("hhhhhpppp");
        System.out.println(user.getRoles());
        return new JwtResponse(jwtLogin.getEmail(),token,user.getRoles().iterator().next().getRoleName());
    }
}

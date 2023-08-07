package com.spring.products.security.configuration;

import com.spring.products.repository.UserRepo;
import com.spring.products.security.jwt.JwtAuthorizationFilter;
import com.spring.products.service.implementation.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    UserRepo userRepo;
    UserService userService;
    @Autowired
    public WebSecurityConfiguration(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepo))
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/api/user/v1/signIn").permitAll()//api/user/v1/signIn
                .antMatchers("/api/user/v1/signUp").permitAll()//api/user/v1/signUp
                .antMatchers("/api/user/v1/signUpAdmin").permitAll()//api/user/v1/signUpAdmin
                .antMatchers("/api/user/v1/confirm").permitAll()
                .antMatchers("/api/history/v3/export-to-pdf").permitAll()

                //.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
               // .antMatchers("/api/data/v1/**").hasRole("admin")
                .anyRequest().authenticated();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider());
    }
    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(password());
        dao.setUserDetailsService(userService);
        return dao;
    }
    @Bean
    public PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
/*
*
* .antMatchers("/api/brand/v3/**").permitAll()
                .antMatchers("/api/product/v3/**").permitAll()
                .antMatchers("/api/category/v3/**").permitAll()
                .antMatchers("/server/v3/**").permitAll()//server/v3
                .antMatchers("/api/order/v3/**").permitAll()///api/history/v1
                .antMatchers("/api/history/v3/**").permitAll()///api/history/v1
                .antMatchers("/api/systemInfo/v3/**").permitAll()
                .antMatchers("/api/serverDetails/v3/**").permitAll()*/
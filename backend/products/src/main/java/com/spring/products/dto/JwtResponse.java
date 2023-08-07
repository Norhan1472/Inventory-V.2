package com.spring.products.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
    private String email;
    private String token;
    private String roleName;
}

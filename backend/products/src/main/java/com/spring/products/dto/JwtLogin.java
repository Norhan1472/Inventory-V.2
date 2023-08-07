package com.spring.products.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtLogin {
    private String email;
    private String password;
}

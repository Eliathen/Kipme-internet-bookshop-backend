package com.bookshop.core.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String tokenType = "Bearer ";

    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}

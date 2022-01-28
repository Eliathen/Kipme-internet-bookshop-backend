package com.bookshop.core.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public JwtAuthenticationResponse(
            @JsonProperty("token_type") String tokenType,
            @JsonProperty("token") String token) {
        this.tokenType = tokenType;
        this.token = token;
    }
}

package com.bookshop.features.user.api.response;

import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.features.user.data.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "email")
        String email,
        @JsonProperty(value = "token_response")
        JwtAuthenticationResponse response
) {
    public static LoginResponse of(
            UserEntity user,
            JwtAuthenticationResponse jwtAuthenticationResponse) {
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                jwtAuthenticationResponse
        );
    }
}

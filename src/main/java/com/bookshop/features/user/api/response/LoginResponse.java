package com.bookshop.features.user.api.response;

import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.features.user.data.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String email;
    private JwtAuthenticationResponse response;

    @JsonCreator
    public LoginResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("email") String email,
            @JsonProperty("token_response") JwtAuthenticationResponse response) {
        this.id = id;
        this.email = email;
        this.response = response;
    }

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

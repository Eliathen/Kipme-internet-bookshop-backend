package com.bookshop.features.user.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginRequest {

    @NotBlank(message = "Provide email address")
    private String email;

    @NotEmpty(message = "Provide password")
    private char[] password;

    @JsonCreator
    public LoginRequest(
            @JsonProperty(value = "email", required = true) String email,
            @JsonProperty(value = "password", required = true) char[] password) {
        this.email = email;
        this.password = password;
    }
}

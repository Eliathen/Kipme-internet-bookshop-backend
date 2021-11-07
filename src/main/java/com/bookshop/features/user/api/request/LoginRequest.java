package com.bookshop.features.user.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
public class LoginRequest {

    @NotBlank(message = "Provide email address")
    private String email;

    @NotEmpty(message = "Provide password")
    private char[] password;

    public LoginRequest(String email, char[] password) {
        this.email = email;
        this.password = password;
    }
}

package com.bookshop.features.user.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public LoginRequest(@JsonProperty("email") String email,
                        @JsonProperty("password") char[] password) {
        this.email = email;
        this.password = password;
    }
}

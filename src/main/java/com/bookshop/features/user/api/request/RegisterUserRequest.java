package com.bookshop.features.user.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "Provide email address")
    private String email;

    @NotNull(message = "Provide password")
    private char[] password;

    @NotBlank(message = "Provide name")
    private String name;

    @NotBlank(message = "Provide surname")
    private String surname;

    @NotBlank(message = "Provide phoneNumber")
    private String phoneNumber;

    public RegisterUserRequest(@JsonProperty("email") String email,
                               @JsonProperty("password") char[] password,
                               @JsonProperty("name") String name,
                               @JsonProperty("surname") String surname,
                               @JsonProperty("phone_number") String phoneNumber

    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}

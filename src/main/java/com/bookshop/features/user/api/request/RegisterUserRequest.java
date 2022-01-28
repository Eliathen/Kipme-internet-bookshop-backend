package com.bookshop.features.user.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public RegisterUserRequest(@JsonProperty(value = "email", required = true) String email,
                               @JsonProperty(value = "password", required = true) char[] password,
                               @JsonProperty(value = "name", required = true) String name,
                               @JsonProperty(value = "surname", required = true) String surname,
                               @JsonProperty(value = "phone_number", required = true) String phoneNumber

    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}

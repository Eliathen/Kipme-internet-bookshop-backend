package com.bookshop.features.user.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;

public record RegisterUserRequest(
        @NotBlank(message = "Provide email address")
        @JsonProperty(value = "email", required = true)
        String email,

        @NotNull(message = "Provide password")
        @JsonProperty(value = "password", required = true)
        char[] password,

        @NotBlank(message = "Provide name")
        @JsonProperty(value = "name", required = true)
        String name,

        @NotBlank(message = "Provide surname")
        @JsonProperty(value = "surname", required = true)
        String surname,

        @NotBlank(message = "Provide phoneNumber")
        @JsonProperty(value = "phone_number", required = true)
        String phoneNumber
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterUserRequest that = (RegisterUserRequest) o;
        return Objects.equals(email, that.email) && Arrays.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(email, name, surname, phoneNumber);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "RegisterUserRequest{" +
                "email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

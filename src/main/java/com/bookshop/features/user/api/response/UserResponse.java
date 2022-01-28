package com.bookshop.features.user.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String email;

    private String name;

    private String surname;

    private String phoneNumber;

    @JsonCreator
    public UserResponse(
            @JsonProperty("email") String email,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("phone_number") String phoneNumber) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}

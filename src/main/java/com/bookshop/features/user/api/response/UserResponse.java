package com.bookshop.features.user.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserResponse(
        @JsonProperty(value = "email")
        String email,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "surname")
        String surname,
        @JsonProperty(value = "phone_number")
        String phoneNumber
) {
}

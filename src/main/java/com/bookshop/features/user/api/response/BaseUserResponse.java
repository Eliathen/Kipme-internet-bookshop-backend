package com.bookshop.features.user.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record BaseUserResponse(
        @JsonProperty(value = "name") String name,
        @JsonProperty(value = "surname") String surname
) {
}

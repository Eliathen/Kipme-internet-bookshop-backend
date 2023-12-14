package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthorResponse(
        @JsonProperty(value = "id")
        Integer id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "surname")
        String surname
) {
}

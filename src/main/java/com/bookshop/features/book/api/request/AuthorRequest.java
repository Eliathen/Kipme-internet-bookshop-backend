package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AuthorRequest(
        @NotBlank(message = "Provide author's surname")
        @JsonProperty(value = "name", required = true)
        String name,
        @NotBlank(message = "Provide author's name")
        @JsonProperty(value = "surname", required = true)
        String surname
) {

}

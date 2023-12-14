package com.bookshop.features.book.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SaveLanguageRequest(
        @NotBlank(message = "Provide language's name")
        @JsonProperty(value = "name", required = true)
        String name

) {
}

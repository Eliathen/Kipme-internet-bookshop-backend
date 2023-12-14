package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AddOpinionRequest(
        @JsonProperty(value = "description", required = true)
        String description,
        @NotBlank(message = "Provide book's rating")
        @JsonProperty(value = "rating", required = true)
        Double rating
) {
}
package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SaveCategoryRequest(
    @NotBlank(message = "Provide category's name")
    @JsonProperty(value = "name", required = true)
    String name
) {
}

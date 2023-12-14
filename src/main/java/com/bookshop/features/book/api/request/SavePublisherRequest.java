package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SavePublisherRequest(
        @NotBlank(message = "Provide publisher's city")
        @JsonProperty(value = "city", required = true)
        String publisherCity,
        @NotBlank(message = "Provide publisher's name")
        @JsonProperty(value = "name", required = true)
        String publisherName

) {

}

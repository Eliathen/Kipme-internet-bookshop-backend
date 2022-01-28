package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AddOpinionRequest {
    private String description;

    @NotBlank(message = "Provide book's rating")
    private Double rating;

    @JsonCreator
    public AddOpinionRequest(
            @JsonProperty("description") String description,
            @JsonProperty(value = "rating", required = true) Double rating) {
        this.description = description;
        this.rating = rating;
    }
}

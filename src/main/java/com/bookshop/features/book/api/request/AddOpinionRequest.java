package com.bookshop.features.book.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AddOpinionRequest {
    private String description;

    @NotBlank(message = "Provide book's rating")
    private Double rating;
}

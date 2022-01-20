package com.bookshop.features.book.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class AddOpinionRequest {
    @NotBlank(message = "Provide rating")
    private Double rating;

    private String description;
}

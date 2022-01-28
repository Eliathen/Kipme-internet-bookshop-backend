package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaveCategoryRequest {

    @NotBlank(message = "Provide category's name")
    private String name;

    @JsonCreator
    public SaveCategoryRequest(@JsonProperty(value = "name", required = true) String name) {
        this.name = name;
    }
}

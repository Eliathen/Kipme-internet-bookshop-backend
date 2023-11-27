package com.bookshop.features.book.api.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveSubcategoryRequest {

    @NotBlank(message = "Provide subcategory's name")
    private String name;

    @JsonCreator

    public SaveSubcategoryRequest(@JsonProperty(value = "name", required = true) String name) {
        this.name = name;
    }
}

package com.bookshop.features.book.api.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaveLanguageRequest {

    @NotBlank(message = "Provide language's name")
    private String name;

    @JsonCreator
    public SaveLanguageRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}

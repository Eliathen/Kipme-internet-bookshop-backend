package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank(message = "Provide author's name")
    private String name;

    @NotBlank(message = "Provide author's surname")
    private String surname;

    @JsonCreator
    public AuthorRequest(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "surname", required = true) String surname) {
        this.name = name;
        this.surname = surname;
    }
}

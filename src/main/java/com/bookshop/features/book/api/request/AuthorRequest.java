package com.bookshop.features.book.api.request;

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
}

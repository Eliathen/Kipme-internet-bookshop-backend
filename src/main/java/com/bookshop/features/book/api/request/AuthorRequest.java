package com.bookshop.features.book.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorRequest {

    private String name;

    private String surname;
}

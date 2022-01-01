package com.bookshop.features.book.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {

    private Integer id;

    private String name;

    private String surname;

}

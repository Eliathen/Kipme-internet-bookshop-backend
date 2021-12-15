package com.bookshop.features.book.api.response;

import lombok.Builder;

@Builder
public class AuthorResponse {

    private Integer id;

    private String name;

    private String surname;

}

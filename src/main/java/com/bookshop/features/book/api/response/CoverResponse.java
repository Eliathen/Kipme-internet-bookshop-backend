package com.bookshop.features.book.api.response;

import lombok.Builder;

@Builder
public class CoverResponse {

    private Long id;

    private String name;

    private byte[] data;

    private String type;

}

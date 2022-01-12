package com.bookshop.features.book.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubcategoryResponse {

    private Integer id;

    private String name;


}

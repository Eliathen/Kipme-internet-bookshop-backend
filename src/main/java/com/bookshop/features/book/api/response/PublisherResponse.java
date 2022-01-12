package com.bookshop.features.book.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PublisherResponse {

    private Integer id;

    private String publisherName;

    private String publisherCity;
}

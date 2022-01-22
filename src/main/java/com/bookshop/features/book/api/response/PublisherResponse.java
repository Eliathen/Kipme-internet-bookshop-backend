package com.bookshop.features.book.api.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonCreator
    public PublisherResponse(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String publisherName,
            @JsonProperty("city") String publisherCity) {
        this.id = id;
        this.publisherName = publisherName;
        this.publisherCity = publisherCity;
    }
}


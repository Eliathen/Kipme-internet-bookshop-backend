package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SavePublisherRequest {

    @NotBlank(message = "Provide publisher's name")
    private String publisherName;

    @NotBlank(message = "Provide publisher's city")
    private String publisherCity;


    @JsonCreator
    public SavePublisherRequest(
            @JsonProperty(value = "name", required = true) String publisherName,
            @JsonProperty(value = "city", required = true) String publisherCity) {
        this.publisherName = publisherName;
        this.publisherCity = publisherCity;
    }
}

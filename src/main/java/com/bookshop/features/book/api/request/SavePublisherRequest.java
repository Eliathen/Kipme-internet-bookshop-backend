package com.bookshop.features.book.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SavePublisherRequest {

    @NotBlank(message = "Provide publisher's name")
    private String publisherName;

    @NotBlank(message = "Provide publisher's city")
    private String publisherCity;

}

package com.bookshop.features.book.mapper;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.data.entity.PublisherEntity;

public class PublisherMapper {

    public static PublisherResponse mapToPublisherResponse(PublisherEntity publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .publisherCity(publisher.getPublisherCity())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    public static PublisherEntity mapToPublisher(SavePublisherRequest request) {
        return PublisherEntity.builder()
                .publisherName(request.getPublisherName())
                .publisherCity(request.getPublisherCity())
                .build();
    }
}

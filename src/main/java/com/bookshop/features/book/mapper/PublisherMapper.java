package com.bookshop.features.book.mapper;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.data.entity.PublisherEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherMapper {

    public static PublisherResponse mapToPublisherResponse(PublisherEntity publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .city(publisher.getPublisherCity())
                .name(publisher.getPublisherName())
                .build();
    }

    public static PublisherEntity mapToPublisher(SavePublisherRequest request) {
        return PublisherEntity.builder()
                .publisherName(request.publisherName())
                .publisherCity(request.publisherCity())
                .build();
    }
}

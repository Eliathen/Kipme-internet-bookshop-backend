package com.bookshop.features.book.mapper;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.model.Publisher;

public class PublisherMapper {

    public static PublisherEntity mapPublisherToPublisherEntity(Publisher publisher) {
        return PublisherEntity.builder()
                .id(publisher.getId())
                .publisherCity(publisher.getPublisherCity())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    public static Publisher mapPublisherEntityToPublisher(PublisherEntity publisherEntity) {
        return Publisher.builder()
                .id(publisherEntity.getId())
                .publisherCity(publisherEntity.getPublisherCity())
                .publisherName(publisherEntity.getPublisherName())
                .build();
    }

    public static PublisherResponse mapPublisherToPublisherResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .publisherCity(publisher.getPublisherCity())
                .publisherName(publisher.getPublisherName())
                .build();
    }

    public static Publisher mapSavePublisherRequestToPublisher(SavePublisherRequest request) {
        return Publisher.builder()
                .publisherName(request.getPublisherName())
                .publisherCity(request.getPublisherCity())
                .build();
    }
}

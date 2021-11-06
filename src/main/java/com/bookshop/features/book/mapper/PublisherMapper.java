package com.bookshop.features.book.mapper;


import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.model.Publisher;

public class PublisherMapper {

    public static PublisherEntity mapToPublisherEntity(Publisher publisher){
        return PublisherEntity.builder()
                .id(publisher.getId())
                .publisherCity(publisher.getPublisherCity())
                .publisherName(publisher.getPublisherName())
                .build();
    }
}

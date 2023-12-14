package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.data.entity.PublisherEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PublisherMapperTest {

    @Test
    void shouldMapPublisherEntityToPublisherResponseCorrect() {
        var entity = PublisherEntity.builder()
                .id(1)
                .publisherName("Publisher 1")
                .publisherCity("City 1")
                .publisherBooks(Collections.emptySet())
                .build();

        var response = PublisherMapper.mapToPublisherResponse(entity);

        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.name()).isEqualTo(entity.getPublisherName());
        assertThat(response.city()).isEqualTo(entity.getPublisherCity());
    }

    @Test
    void shouldMapSavePublisherRequestToPublisherEntityCorrect() {
        var request = new SavePublisherRequest("Publisher 1", "City 1");

        var entity = PublisherMapper.mapToPublisher(request);

        assertThat(entity.getPublisherName()).isEqualTo(request.publisherName());
        assertThat(entity.getPublisherCity()).isEqualTo(request.publisherCity());
    }
}
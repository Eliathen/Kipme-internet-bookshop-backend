package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.data.entity.PublisherEntity;

import java.util.List;

public interface PublisherService {

    List<PublisherEntity> getPublishers();

    PublisherEntity savePublisher(PublisherEntity publisher);

    PublisherEntity getPublisher(Integer id);

    List<PublisherEntity> getPublishers(List<Integer> publishersIds);
}

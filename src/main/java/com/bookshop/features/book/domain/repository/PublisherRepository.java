package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {

    List<PublisherEntity> getPublishers();

    PublisherEntity save(PublisherEntity publisher);

    Optional<PublisherEntity> getPublisher(Integer id);

    List<PublisherEntity> getPublishersByIds(List<Integer> publishersIds);
}

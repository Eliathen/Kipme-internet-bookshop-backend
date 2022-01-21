package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.PublisherJpaRepository;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryImpl implements PublisherRepository {

    private final PublisherJpaRepository jpa;

    @Override
    public List<PublisherEntity> getPublishers() {
        return jpa.findAll();
    }

    @Override
    public PublisherEntity save(PublisherEntity publisher) {
        return jpa.saveAndFlush(publisher);
    }

    @Override
    public Optional<PublisherEntity> getPublisher(Integer id) {
        return jpa.findById(id);
    }

    @Override
    public List<PublisherEntity> getPublishersByIds(List<Integer> publishersIds) {
        return jpa.findAllById(publishersIds);
    }
}

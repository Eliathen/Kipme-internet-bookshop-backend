package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.data.jpa.PublisherJpaRepository;
import com.bookshop.features.book.domain.model.Publisher;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import com.bookshop.features.book.exception.PublisherNotFound;
import com.bookshop.features.book.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PublisherRepositoryImpl implements PublisherRepository {

    private final PublisherJpaRepository jpa;

    @Override
    public List<Publisher> getPublishers() {
        return jpa.findAll().stream().map(PublisherMapper::mapToPublisher).collect(Collectors.toList());
    }

    @Override
    public Publisher save(Publisher publisher) {
        return PublisherMapper.mapToPublisher(jpa.saveAndFlush(PublisherMapper.mapToPublisherEntity(publisher)));
    }

    @Override
    public Publisher getPublisher(Integer id) {
        return PublisherMapper.mapToPublisher(jpa.findById(id).orElseThrow(() -> new PublisherNotFound(id)));
    }

    @Override
    public List<Publisher> getPublishersByIds(List<Integer> publishersIds) {
        return jpa.findAllById(publishersIds).stream().map(PublisherMapper::mapToPublisher).collect(Collectors.toList());
    }
}

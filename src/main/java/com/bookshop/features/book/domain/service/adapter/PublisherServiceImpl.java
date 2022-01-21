package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.exception.PublisherNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    @Override
    public List<PublisherEntity> getPublishers() {
        return repository.getPublishers();
    }

    @Override
    public PublisherEntity savePublisher(PublisherEntity publisher) {
        return repository.save(publisher);
    }

    @Override
    public PublisherEntity getPublisher(Integer id) {
        return repository.getPublisher(id).orElseThrow(()-> new PublisherNotFound(id));
    }

    @Override
    public List<PublisherEntity> getPublishers(List<Integer> publishersIds) {
        return repository.getPublishersByIds(publishersIds);
    }
}

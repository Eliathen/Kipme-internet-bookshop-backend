package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.domain.model.Publisher;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    @Override
    public List<Publisher> getPublishers() {
        return repository.getPublishers();
    }

    @Override
    public Publisher savePublisher(Publisher publisher) {
        return repository.save(publisher);
    }

    @Override
    public Publisher getPublisher(Integer id) {
        return repository.getPublisher(id);
    }

    @Override
    public List<Publisher> getPublishers(List<Integer> publishersIds) {
        return repository.getPublishersByIds(publishersIds);
    }
}

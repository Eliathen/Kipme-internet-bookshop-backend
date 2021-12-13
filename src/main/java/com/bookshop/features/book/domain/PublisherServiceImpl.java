package com.bookshop.features.book.domain;

import com.bookshop.features.book.api.service.PublisherService;
import com.bookshop.features.book.domain.model.Publisher;
import com.bookshop.features.book.domain.repository.PublisherRepository;
import com.bookshop.features.book.mapper.PublisherMapper;
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

}

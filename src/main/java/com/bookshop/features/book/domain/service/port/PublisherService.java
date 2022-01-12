package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.domain.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getPublishers();

    Publisher savePublisher(Publisher publisher);

    Publisher getPublisher(Integer id);

    List<Publisher> getPublishers(List<Integer> publishersIds);
}

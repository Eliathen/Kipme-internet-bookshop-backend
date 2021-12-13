package com.bookshop.features.book.api.service;

import com.bookshop.features.book.domain.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getPublishers();

    Publisher savePublisher(Publisher publisher);
}

package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.PublisherEntity;
import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.model.Publisher;

import java.util.List;

public interface PublisherRepository {

    List<Publisher> getPublishers();

    Publisher save(Publisher publisher);
}

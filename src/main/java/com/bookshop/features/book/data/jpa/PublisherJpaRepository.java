package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherJpaRepository extends JpaRepository<PublisherEntity, Integer> {
}

package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {
}

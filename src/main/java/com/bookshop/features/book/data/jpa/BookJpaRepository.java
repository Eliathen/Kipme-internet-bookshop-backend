package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> getBookEntityById(Long id);

}

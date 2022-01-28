package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> getBookEntityById(Long id);

    @Query(value = "SELECT distinct * from Book b " +
            "join BOOK_AUTHORS ba on ba.BOOK_ID = b.ID " +
            "join AUTHOR a on a.ID = ba.AUTHOR_ID " +
            " WHERE LOWER(b.TITLE) LIKE concat('%', concat(?1, '%'))" +
            "OR a.name = ?1 OR a.surname = ?1", nativeQuery = true)
    List<BookEntity> getBookEntityByTitleQuery(String query);

    Optional<BookEntity> getBookEntityByIsbn(String isbn);
}

package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.jpa.BookJpaRepository;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.bookshop.features.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository jpa;

    @Override
    public Book saveBook(Book book) {
        BookEntity bookToSave = BookMapper.mapToBookEntity(book);
        return BookMapper.mapToBook(jpa.saveAndFlush(bookToSave));
    }
}

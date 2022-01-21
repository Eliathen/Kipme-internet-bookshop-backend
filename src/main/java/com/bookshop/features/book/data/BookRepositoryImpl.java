package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.jpa.BookJpaRepository;
import com.bookshop.features.book.data.jpa.OpinionJpaRepository;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository jpa;
    private final UserRepository userRepository;
    private final OpinionJpaRepository opinionJpaRepository;

    @Override
    public BookEntity saveBook(BookEntity book) {
        return jpa.saveAndFlush(book);
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return jpa.getBookEntityById(id);
    }

    @Override
    public void saveOpinion(OpinionEntity opinion) {
        opinionJpaRepository.saveAndFlush(opinion);
    }
}

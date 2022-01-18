package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.AuthorJpaRepository;
import com.bookshop.features.book.domain.model.Author;
import com.bookshop.features.book.domain.repository.AuthorRepository;
import com.bookshop.features.book.exception.AuthorNotFound;
import com.bookshop.features.book.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final AuthorJpaRepository jpa;

    @Override
    public Author getAuthorByNameAndSurname(Author author) {
        return AuthorMapper.mapToAuthor(jpa.findFirstByNameIgnoreCaseAndSurnameIgnoreCase(author.getName(), author.getSurname())
                .orElseGet(() -> jpa.saveAndFlush(AuthorMapper.mapToAuthorEntity(author))));
    }

    @Override
    public Author saveAuthor(Author author) {
        return AuthorMapper.mapToAuthor(jpa.saveAndFlush(AuthorMapper.mapToAuthorEntity(author)));
    }

    @Override
    public Author getAuthorById(Integer id) {
        return AuthorMapper.mapToAuthor(jpa.findById(id).orElseThrow(() -> new AuthorNotFound(id)));
    }
}

package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.AuthorEntity;

import java.util.Optional;

public interface AuthorRepository {

    AuthorEntity getAuthorByNameAndSurnameOrSave(String name, String surname);

    AuthorEntity saveAuthor(AuthorEntity author);

    Optional<AuthorEntity> getAuthorById(Integer id);
}

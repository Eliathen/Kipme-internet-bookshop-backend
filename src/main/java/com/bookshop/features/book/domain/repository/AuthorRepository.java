package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    AuthorEntity getAuthorByNameAndSurnameOrSave(String name, String surname);

    List<AuthorEntity> getAuthorByNameOrSurname(String nameOrSurname);

    AuthorEntity saveAuthor(AuthorEntity author);

    Optional<AuthorEntity> getAuthorById(Integer id);
}

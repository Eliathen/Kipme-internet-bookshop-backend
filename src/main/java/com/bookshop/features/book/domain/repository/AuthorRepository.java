package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.domain.model.Author;

public interface AuthorRepository {

    Author getAuthorByNameAndSurname(Author author);

    Author saveAuthor(Author author);

    Author getAuthorById(Integer id);
}

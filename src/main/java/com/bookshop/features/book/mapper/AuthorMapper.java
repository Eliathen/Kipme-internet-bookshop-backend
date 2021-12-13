package com.bookshop.features.book.mapper;

import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.domain.model.Author;

public class AuthorMapper {

    public static AuthorEntity mapToAuthorEntity(Author author){
        return AuthorEntity.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    public static Author mapToAuthor(AuthorEntity authorEntity) {
        return Author.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .surname(authorEntity.getSurname())
                .build();
    }
}

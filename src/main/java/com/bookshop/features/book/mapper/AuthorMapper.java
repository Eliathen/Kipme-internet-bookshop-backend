package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AuthorRequest;
import com.bookshop.features.book.api.response.AuthorResponse;
import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.domain.model.Author;

import java.util.Collections;
import java.util.LinkedList;

public class AuthorMapper {

    public static AuthorEntity mapToAuthorEntity(Author author){
        return AuthorEntity.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .authorsBooks(new LinkedList<>())
                .build();
    }

    public static Author mapToAuthor(AuthorEntity authorEntity) {
        return Author.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .surname(authorEntity.getSurname())
                .build();
    }

    public static AuthorResponse mapToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    public static Author mapToAuthor(AuthorRequest author) {
        return Author.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}

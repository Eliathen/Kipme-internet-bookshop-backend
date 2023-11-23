package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AuthorRequest;
import com.bookshop.features.book.api.response.AuthorResponse;
import com.bookshop.features.book.data.entity.AuthorEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorMapper {

    public static AuthorResponse mapToAuthorResponse(AuthorEntity authorEntity) {
        return AuthorResponse.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .surname(authorEntity.getSurname())
                .build();
    }

    public static AuthorEntity mapAuthorRequestToAuthor(AuthorRequest author) {
        return AuthorEntity.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}

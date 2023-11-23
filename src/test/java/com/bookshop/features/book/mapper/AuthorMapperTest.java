package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AuthorRequest;
import com.bookshop.features.book.data.entity.AuthorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthorMapperTest {

    @Test
    void shouldMapAuthorEntityToAuthorResponseCorrect() {
        //given
        var author = AuthorEntity.builder()
                .id(1)
                .name("Peter")
                .surname("Brett").build();

        //when
        var result = AuthorMapper.mapToAuthorResponse(author);

        //then
        Assertions.assertEquals(author.getId(), result.getId());
        Assertions.assertEquals(author.getName(), result.getName());
        Assertions.assertEquals(author.getSurname(), result.getSurname());
    }

    @Test
    void shouldMapSaveLanguageRequestToLanguageEntityCorrect() {
        //given
        var request = new AuthorRequest("Peter", "Brett");
        //when
        var result = AuthorMapper.mapAuthorRequestToAuthor(request);
        //then
        Assertions.assertEquals(request.getName(), result.getName());
        Assertions.assertEquals(request.getSurname(), result.getSurname());
    }

}
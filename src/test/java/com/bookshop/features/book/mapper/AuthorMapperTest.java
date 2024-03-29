package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AuthorRequest;
import com.bookshop.features.book.data.entity.AuthorEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    @Test
    void shouldMapAuthorEntityToAuthorResponseCorrect() {
        //given
        var entity = AuthorEntity.builder()
                .id(1)
                .name("Peter")
                .surname("Brett").build();

        //when
        var response = AuthorMapper.mapToAuthorResponse(entity);

        //then
        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.name()).isEqualTo(entity.getName());
        assertThat(response.surname()).isEqualTo(entity.getSurname());
    }

    @Test
    void shouldMapSaveAuthorRequestToAuthorEntityCorrect() {
        //given
        var request = new AuthorRequest("Peter", "Brett");
        //when
        var result = AuthorMapper.mapAuthorRequestToAuthor(request);
        //then
        assertThat(result.getName()).isEqualTo(request.name());
        assertThat(result.getSurname()).isEqualTo(request.surname());
    }

}
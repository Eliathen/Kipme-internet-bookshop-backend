package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LanguageMapperTest {

    @Test
    void shouldMapLanguageEntityToLanguageResponseCorrect() {
        //given
        var entity = new LanguageEntity(1, "name", Set.of());

        //when
        var response = LanguageMapper.mapToLanguageResponse(entity);

        //then
        assertThat(response.id()).isEqualTo(entity.getId());
        assertThat(response.name()).isEqualTo(entity.getName());

    }

    @Test
    void shouldMapSaveLanguageRequestToLanguageEntityCorrect() {
        //given
        var request = new SaveLanguageRequest("name");
        //when
        var response = LanguageMapper.mapToLanguageEntity(request);
        //then

        assertThat(response.getName()).isEqualTo(request.name());
    }

}
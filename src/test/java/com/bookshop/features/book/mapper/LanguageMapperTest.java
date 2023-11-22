package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class LanguageMapperTest {

    @Test
    void shouldMapLanguageEntityToLanguageResponseCorrect() {
        //given
        var entity = new LanguageEntity(1, "name", Set.of());

        //when
        var result = LanguageMapper.mapToLanguageResponse(entity);

        //then
        Assertions.assertEquals(entity.getId(), result.getId());
        Assertions.assertEquals(entity.getName(), result.getName());
    }

    @Test
    void shouldMapSaveLanguageRequestToLanguageEntityCorrect() {
        //given
        var request = new SaveLanguageRequest("name");
        //when
        var result = LanguageMapper.mapToLanguageEntity(request);
        //then
        Assertions.assertEquals(request.getName(), result.getName());
    }

}
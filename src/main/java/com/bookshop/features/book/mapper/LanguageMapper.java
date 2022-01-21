package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.data.entity.LanguageEntity;

public class LanguageMapper {

    public static LanguageEntity mapToLanguageEntity(SaveLanguageRequest reguest) {
        return LanguageEntity.builder()
                .name(reguest.getName())
                .build();
    }

    public static LanguageResponse mapToLanguageResponse(LanguageEntity language) {
        return LanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

}

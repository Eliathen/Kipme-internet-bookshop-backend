package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.domain.model.Language;

public class LanguageMapper {

    public static LanguageEntity mapToLanguageEntity(Language language) {
        return LanguageEntity.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public static Language mapToLanguage(LanguageEntity language) {
        return Language.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public static LanguageResponse mapToLanguageResponse(Language language) {
        return LanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public static Language mapToLanguage(SaveLanguageRequest request) {
        return Language.builder()
                .name(request.getName())
                .build();
    }
}

package com.bookshop.features.book.mapper;

import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.domain.model.Language;

public class LanguageMapper {

    public static LanguageEntity mapToLanguageEntity(Language language){
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
}

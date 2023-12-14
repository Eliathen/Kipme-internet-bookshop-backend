package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.data.entity.LanguageEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageMapper {


    public static LanguageEntity mapToLanguageEntity(SaveLanguageRequest request) {
        return LanguageEntity.builder()
                .name(request.name())
                .build();
    }

    public static LanguageResponse mapToLanguageResponse(LanguageEntity language) {
        return LanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

}

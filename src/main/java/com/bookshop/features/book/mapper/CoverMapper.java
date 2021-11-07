package com.bookshop.features.book.mapper;


import com.bookshop.features.book.data.entity.CoverEntity;
import com.bookshop.features.book.domain.model.Cover;
import lombok.Builder;

@Builder
public class CoverMapper {
    public static CoverEntity mapToCoverEntity(Cover cover) {
        return CoverEntity.builder()
                .id(cover.getId())
                .data(cover.getData())
                .name(cover.getName())
                .type(cover.getType())
                .build();
    }
}

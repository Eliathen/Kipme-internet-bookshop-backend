package com.bookshop.features.book.mapper;


import com.bookshop.features.book.api.response.CoverResponse;
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

    public static Cover mapToCover(CoverEntity cover) {
        return Cover.builder()
                .id(cover.getId())
                .data(cover.getData())
                .name(cover.getName())
                .type(cover.getType())
                .build();
    }

    public static CoverResponse mapToCoverResponse(Cover cover) {
        return CoverResponse.builder()
                .id(cover.getId())
                .name(cover.getName())
                .type(cover.getType())
                .data(cover.getData())
                .build();
    }
}

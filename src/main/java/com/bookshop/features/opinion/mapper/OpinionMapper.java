package com.bookshop.features.opinion.mapper;

import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.opinion.domain.model.Opinion;

public class OpinionMapper {
    public static OpinionEntity mapToOpinionEntity(Opinion opinion) {

        return OpinionEntity.builder()
                .id(opinion.getId())
                .date(opinion.getDate())
                .description(opinion.getDescription())
                .build();
    }
}

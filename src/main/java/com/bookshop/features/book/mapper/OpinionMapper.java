package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.response.OpinionResponse;
import com.bookshop.features.book.data.entity.OpinionEntity;
import com.bookshop.features.user.mapper.UserMapper;

import java.time.LocalDateTime;

public class OpinionMapper {

    public static OpinionEntity mapToOpinionEntity(AddOpinionRequest request) {
        return OpinionEntity.builder()
                .date(LocalDateTime.now())
                .description(request.description())
                .rating(request.rating())
                .build();
    }

    public static OpinionResponse mapOpinionEntityToOpinionResponse(OpinionEntity opinion) {
        return OpinionResponse.builder()
                .id(opinion.getId())
                .date(opinion.getDate())
                .description(opinion.getDescription())
                .rating(opinion.getRating())
                .user(UserMapper.mapToBaseUserResponse(opinion.getUser()))
                .build();
    }
}

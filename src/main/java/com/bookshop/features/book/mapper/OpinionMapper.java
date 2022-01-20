package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.response.OpinionResponse;
import com.bookshop.features.book.data.entity.OpinionEntity;
import com.bookshop.features.book.domain.model.Opinion;
import com.bookshop.features.user.mapper.UserMapper;

import java.time.LocalDateTime;

public class OpinionMapper {
    public static OpinionEntity mapToOpinionEntity(Opinion opinion) {

        return OpinionEntity.builder()
                .id(opinion.getId())
                .rating(opinion.getRating())
                .user(UserMapper.mapToUserEntity(opinion.getUser()))
                .date(opinion.getDate())
                .description(opinion.getDescription())
                .build();
    }

    public static Opinion mapToOpinion(AddOpinionRequest request) {
        return Opinion.builder()
                .date(LocalDateTime.now())
                .rating(request.getRating())
                .description(request.getDescription())
                .build();
    }

    public static Opinion mapToOpinion(OpinionEntity opinionEntity) {
        return Opinion.builder()
                .id(opinionEntity.getId())
                .description(opinionEntity.getDescription())
                .rating(opinionEntity.getRating())
                .user(UserMapper.mapToUser(opinionEntity.getUser()))
                .date(opinionEntity.getDate())
                .build();
    }

    public static OpinionResponse mapToOpinionResponse(Opinion opinion) {
        return OpinionResponse.builder()
                .id(opinion.getId())
                .date(opinion.getDate())
                .rating(opinion.getRating())
                .user(UserMapper.mapToUserResponse(opinion.getUser()))
                .description(opinion.getDescription())
                .build();
    }
}

package com.bookshop.features.opinion.mapper;

import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.response.OpinionResponse;
import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.opinion.domain.model.Opinion;
import com.bookshop.features.user.mapper.UserMapper;

import java.time.LocalDateTime;

public class OpinionMapper {
    public static OpinionEntity mapToOpinionEntity(Opinion opinion) {

        return OpinionEntity.builder()
                .id(opinion.getId())
                .user(UserMapper.mapToUserEntity(opinion.getUser()))
                .date(opinion.getDate())
                .description(opinion.getDescription())
                .build();
    }

    public static Opinion mapToOpinion(AddOpinionRequest request) {
        return Opinion.builder()
                .date(LocalDateTime.now())
                .description(request.getDescription())
                .build();
    }

    public static Opinion mapToOpinion(OpinionEntity opinionEntity){
        return Opinion.builder()
                .id(opinionEntity.getId())
                .description(opinionEntity.getDescription())
                .user(UserMapper.mapToUser(opinionEntity.getUser()))
                .date(opinionEntity.getDate())
                .build();
    }

    public static OpinionResponse mapToOpinionResponse(Opinion opinion) {
        return OpinionResponse.builder()
                .id(opinion.getId())
                .date(opinion.getDate())
                .user(UserMapper.mapToUserResponse(opinion.getUser()))
                .description(opinion.getDescription())
                .build();
    }
}

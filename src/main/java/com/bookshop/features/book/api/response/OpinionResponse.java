package com.bookshop.features.book.api.response;


import com.bookshop.features.user.api.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OpinionResponse {

    private Integer id;

    private String description;

    private LocalDateTime date;

    private UserResponse user;

    private Double rating;

}
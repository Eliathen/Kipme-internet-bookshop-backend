package com.bookshop.features.book.api.response;


import com.bookshop.features.user.api.response.BaseUserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OpinionResponse(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("description")
        String description,
        @JsonProperty("date")
        LocalDateTime date,
        @JsonProperty("user")
        BaseUserResponse user,
        @JsonProperty("rating")
        Double rating
) {


}
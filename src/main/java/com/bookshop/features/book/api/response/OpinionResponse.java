package com.bookshop.features.book.api.response;


import com.bookshop.features.user.api.response.BaseUserResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OpinionResponse {

    private Integer id;

    private String description;

    private LocalDateTime date;

    private BaseUserResponse user;

    private Double rating;

    @JsonCreator
    public OpinionResponse(
            @JsonProperty("id") Integer id,
            @JsonProperty("description") String description,
            @JsonProperty("date") LocalDateTime date,
            @JsonProperty("user") BaseUserResponse user,
            @JsonProperty("rating") Double rating) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.user = user;
        this.rating = rating;
    }
}
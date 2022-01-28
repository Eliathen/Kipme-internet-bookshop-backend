package com.bookshop.features.user.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseUserResponse {

    private String name;

    private String surname;

    @JsonCreator
    public BaseUserResponse(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname) {
        this.name = name;
        this.surname = surname;
    }
}

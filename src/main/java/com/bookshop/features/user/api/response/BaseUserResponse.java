package com.bookshop.features.user.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseUserResponse {

    protected String name;

    protected String surname;

}

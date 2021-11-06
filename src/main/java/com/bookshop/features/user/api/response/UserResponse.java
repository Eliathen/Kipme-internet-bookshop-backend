package com.bookshop.features.user.api.response;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class UserResponse {

    private String email;

    private String name;

    private String surname;

    private String phoneNumber;

}

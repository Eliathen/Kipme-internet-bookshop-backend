package com.bookshop.features.book.api.response;


import com.bookshop.features.user.api.response.UserResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OpinionResponse {

    private Integer id;

    private String description;

    private LocalDateTime date;

    private UserResponse user;

}
package com.bookshop.features.book.domain.model;

import com.bookshop.features.user.domain.model.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Opinion {

    private Integer id;

    private String description;

    private LocalDateTime date;

    private Double rating;

    private User user;

}

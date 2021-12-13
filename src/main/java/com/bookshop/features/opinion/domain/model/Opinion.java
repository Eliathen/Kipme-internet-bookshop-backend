package com.bookshop.features.opinion.domain.model;

import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.domain.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

}

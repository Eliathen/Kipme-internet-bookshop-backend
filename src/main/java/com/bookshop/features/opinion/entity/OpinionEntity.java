package com.bookshop.features.opinion.entity;

import com.bookshop.features.book.entity.BookEntity;
import com.bookshop.features.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Opinion")
public class OpinionEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private LocalDate date;

    /*
    private BookEntity bookId;
    private UserEntity userId;
     */

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="book_Id")
    private BookEntity book;

}

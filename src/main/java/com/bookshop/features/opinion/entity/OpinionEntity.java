package com.bookshop.features.opinion.entity;

import com.bookshop.features.book.entity.BookEntity;
import com.bookshop.features.user.entity.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    @ManyToOne
    private UserEntity user;

    @Getter
    @Setter
    @ManyToOne
    private BookEntity book;

    public OpinionEntity() {
    }

    public OpinionEntity(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "OpinionEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

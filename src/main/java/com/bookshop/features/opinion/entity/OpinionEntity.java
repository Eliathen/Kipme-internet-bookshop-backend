package com.bookshop.features.opinion.entity;

import com.bookshop.features.book.entity.BookEntity;
import com.bookshop.features.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Opinion")
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;
    private LocalDate date;

    /*
    private BookEntity bookId;
    private UserEntity userId;
     */

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private BookEntity book;

    public OpinionEntity() {
    }

    public OpinionEntity(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpinionEntity that = (OpinionEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

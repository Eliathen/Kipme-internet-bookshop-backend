package com.bookshop.features.author.entity;

import com.bookshop.features.book.entity.BookEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String surname;

    @ManyToMany(mappedBy = "bookAuthors")
    private Set<BookEntity> authorsBooks = new HashSet<>();

    public AuthorEntity() {
    }

    public AuthorEntity(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Set<BookEntity> getAuthorsBooks() {
        return authorsBooks;
    }

    public void setAuthorsBooks(Set<BookEntity> authorsBooks) {
        this.authorsBooks = authorsBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

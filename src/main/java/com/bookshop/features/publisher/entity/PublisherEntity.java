package com.bookshop.features.publisher.entity;

import com.bookshop.features.book.entity.BookEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String publisherName;
    private String publicherCity;

    @ManyToMany(mappedBy = "bookPublishers")
    private Set<BookEntity> publisherBooks = new HashSet<>();

    public PublisherEntity() {
    }

    public PublisherEntity(String publisherName, String publicherCity) {
        this.publisherName = publisherName;
        this.publicherCity = publicherCity;
    }

    public Set<BookEntity> getPublisherBooks() {
        return publisherBooks;
    }

    public void setPublisherBooks(Set<BookEntity> publisherBooks) {
        this.publisherBooks = publisherBooks;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublicherCity() {
        return publicherCity;
    }

    public void setPublicherCity(String publicherCity) {
        this.publicherCity = publicherCity;
    }

    @Override
    public String toString() {
        return "PublisherEntity{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", publicherCity='" + publicherCity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherEntity that = (PublisherEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

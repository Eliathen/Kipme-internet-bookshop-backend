package com.bookshop.features.publisher.entity;

import com.bookshop.features.book.entity.BookEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Entity(name = "Publisher")
public class PublisherEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String publisherName;

    @Getter
    @Setter
    private String publicherCity;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "bookPublishers")
    private Set<BookEntity> publisherBooks = new HashSet<>();

    public PublisherEntity() {
    }

    public PublisherEntity(String publisherName, String publicherCity) {
        this.publisherName = publisherName;
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
}

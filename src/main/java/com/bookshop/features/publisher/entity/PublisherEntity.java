package com.bookshop.features.publisher.entity;

import com.bookshop.features.book.entity.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
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
    private String publisherCity;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "bookPublishers")
    private Set<BookEntity> publisherBooks = new HashSet<>();

}

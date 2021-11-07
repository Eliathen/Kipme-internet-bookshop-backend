package com.bookshop.features.book.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
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

package com.bookshop.features.book.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "Author")
public class AuthorEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "bookAuthors")
    private Set<BookEntity> authorsBooks = new HashSet<>();

}

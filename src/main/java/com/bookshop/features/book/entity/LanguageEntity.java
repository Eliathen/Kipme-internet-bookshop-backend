package com.bookshop.features.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Language")
public class LanguageEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "language")
    private Set<BookEntity> books = Collections.emptySet();

}

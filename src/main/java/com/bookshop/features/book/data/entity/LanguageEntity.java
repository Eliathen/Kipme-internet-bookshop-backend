package com.bookshop.features.book.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Builder
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

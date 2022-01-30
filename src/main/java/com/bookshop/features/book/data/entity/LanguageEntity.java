package com.bookshop.features.book.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LANGUAGE")
public class LanguageEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "language")
    private Set<BookEntity> books = Collections.emptySet();

}

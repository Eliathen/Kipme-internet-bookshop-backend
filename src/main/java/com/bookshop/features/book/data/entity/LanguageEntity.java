package com.bookshop.features.book.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LANGUAGE")
@Getter
@Setter
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "language")
    private Set<BookEntity> books = Collections.emptySet();

}

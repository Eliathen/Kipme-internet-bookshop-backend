package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Category")
public class CategoryEntity {

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Set<SubcategoryEntity> subcategories = Collections.emptySet();

    @Getter
    @Setter
    @OneToMany(mappedBy = "category")
    private Set<BookEntity> books = Collections.emptySet();

}

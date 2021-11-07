package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "category")
    private Set<SubcategoryEntity> subcategories = Collections.emptySet();

    @Getter
    @Setter
    @OneToMany(mappedBy = "category")
    private Set<BookEntity> books = Collections.emptySet();

}

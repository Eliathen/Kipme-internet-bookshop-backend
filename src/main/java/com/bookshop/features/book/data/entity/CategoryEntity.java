package com.bookshop.features.book.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

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

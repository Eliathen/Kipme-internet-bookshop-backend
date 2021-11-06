package com.bookshop.features.book.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Subcategory")
public class SubcategoryEntity {


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
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private CategoryEntity category;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "subcategories")
    private Set<BookEntity> books = Collections.emptySet();

}

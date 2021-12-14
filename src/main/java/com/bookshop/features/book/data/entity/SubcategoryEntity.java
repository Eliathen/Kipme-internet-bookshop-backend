package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Builder
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
    private CategoryEntity category;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "subcategories")
    private Set<BookEntity> books = Collections.emptySet();

}

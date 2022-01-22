package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Subcategory")
public class SubcategoryEntity {


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
    @ManyToOne
    private CategoryEntity category;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "subcategories")
    private List<BookEntity> books;

}

package com.bookshop.features.book.domain.model;


import com.bookshop.features.book.data.entity.SubcategoryEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    private Integer id;

    private String name;

    private Set<Subcategory> subcategories = Collections.emptySet();

}

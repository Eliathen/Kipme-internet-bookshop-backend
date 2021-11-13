package com.bookshop.features.book.domain.model;


import com.bookshop.features.book.data.entity.SubcategoryEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    private Integer id;

    private String name;

    private List<Subcategory> subcategories = Collections.emptyList();

}

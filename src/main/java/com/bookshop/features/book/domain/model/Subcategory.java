package com.bookshop.features.book.domain.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Subcategory {

    private Integer id;

    private String name;

}

package com.bookshop.features.book.domain.model;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Publisher {

    private Integer id;

    private String publisherName;

    private String publisherCity;

}

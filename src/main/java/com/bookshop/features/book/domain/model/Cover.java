package com.bookshop.features.book.domain.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cover {

    private Long id;

    private String name;

    private byte[] data;

    private String type;

}

package com.bookshop.features.book.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Entity(name = "Cover")
public class CoverEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Lob
    private byte[] data;

    @Getter
    @Setter
    private String type;

}

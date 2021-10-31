package com.bookshop.features.book.entity;

import com.bookshop.features.book.entity.BookEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

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

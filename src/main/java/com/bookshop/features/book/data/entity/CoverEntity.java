package com.bookshop.features.book.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COVER")
public class CoverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @JdbcType(VarbinaryJdbcType.class)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    private String type;

}

package com.bookshop.features.magazine.data.entity;


import com.bookshop.features.book.data.entity.BookEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MAGAZINE_STATE")
@Builder
public class MagazineStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "magazineState")
    private BookEntity book;

    private int amount;

}

package com.bookshop.features.magazine.data.entity;


import com.bookshop.features.book.data.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MAGAZINE_STATE")
public class MagazineStateEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private BookEntity book;

    private int amount;

}

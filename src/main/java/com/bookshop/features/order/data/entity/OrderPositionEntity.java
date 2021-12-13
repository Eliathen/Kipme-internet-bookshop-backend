package com.bookshop.features.order.data.entity;


import com.bookshop.features.book.data.entity.BookEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Order_Position")
@NoArgsConstructor
@Getter
@Setter
public class OrderPositionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private BookEntity orderedBook;

    private BigInteger price;

}

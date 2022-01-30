package com.bookshop.features.order.data.entity;


import com.bookshop.features.book.data.entity.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "ORDER_POSITION")
public class OrderPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private BookEntity orderedBook;

    private BigDecimal price;

}

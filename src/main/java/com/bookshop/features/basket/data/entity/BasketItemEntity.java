package com.bookshop.features.basket.data.entity;


import com.bookshop.features.book.data.entity.BookEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BASKET_ITEM")
public class BasketItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private BasketEntity basketEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "BOOK_ID")
    private BookEntity book;

    private Integer quantity;

    public BigDecimal getSumPrice() {
        return book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 1) {
            quantity = quantity - 1;
        }
    }
}

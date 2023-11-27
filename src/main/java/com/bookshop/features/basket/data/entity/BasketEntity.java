package com.bookshop.features.basket.data.entity;


import com.bookshop.features.user.data.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BASKET")
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basketEntity", orphanRemoval = true)
    private List<BasketItemEntity> items;

    @OneToOne(mappedBy = "basketEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private UserEntity userEntity;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    public void addItem(BasketItemEntity item) {
        if (items == null) {
            items = new ArrayList<>(List.of(item));
        } else {
            items.add(item);
        }
        item.setBasketEntity(this);
    }

    public void removeItem(BasketItemEntity item) {
        items.remove(item);
        item.setBasketEntity(this);
    }

    public BigDecimal getBasketFullPrice() {
        return (items != null) ? items.stream().map(BasketItemEntity::getSumPrice).reduce(BigDecimal.ZERO, BigDecimal::add) : BigDecimal.ZERO;
    }

    public void clearBasket() {
        items.clear();
    }
}


package com.bookshop.features.order.entity;


import com.bookshop.features.book.entity.BookEntity;
import com.bookshop.features.user.entity.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private LocalDate orderDate;

    @ManyToOne
    private UserEntity user;

    @ManyToMany(mappedBy = "bookOrders")
    private Set<BookEntity> orderedBooks = new HashSet<>();

   /*
    private BookEntity bookId;
    private DeliveryType deliveryType; ENUM
    private OrderStatus orderStatus; ENUM
    */

    public OrderEntity() {
    }

    public OrderEntity(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

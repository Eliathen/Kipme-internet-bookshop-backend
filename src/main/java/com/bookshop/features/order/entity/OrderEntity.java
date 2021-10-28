package com.bookshop.features.order.entity;


import com.bookshop.features.address.entity.AddressEntity;
import com.bookshop.features.book.entity.BookEntity;
import com.bookshop.features.payment.entity.PaymentEntity;
import com.bookshop.features.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Procurement")
public class OrderEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Getter
    @Setter
    private LocalDateTime orderDate;

    @Getter
    @Setter
    @ManyToOne
    private UserEntity user;

    @Getter
    @Setter
    @ManyToOne
    private AddressEntity address;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "bookOrders")
    private Set<BookEntity> orderedBooks = new HashSet<>();

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "delivery_type_id")
    private DeliveryTypeEntity deliveryType;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatusEntity orderStatus;

}

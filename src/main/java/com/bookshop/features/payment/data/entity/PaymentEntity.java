package com.bookshop.features.payment.data.entity;

import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Payment")
public class PaymentEntity {

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
    private BigDecimal amount;

    @Getter
    @Setter
    @OneToOne(mappedBy = "payment")
    private OrderEntity order;


    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentTypeEntity paymentType;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatusEntity paymentStatus;


}

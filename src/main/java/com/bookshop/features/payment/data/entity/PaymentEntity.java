package com.bookshop.features.payment.data.entity;

import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "Payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

    @ManyToOne()
    @Enumerated(value = EnumType.STRING)
    @JoinColumn(name = "PAYMENT_ID", nullable = false)
    private PaymentMethodEntity paymentMethodEntity;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEntity paymentStatus;

}

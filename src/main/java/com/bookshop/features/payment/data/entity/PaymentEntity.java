package com.bookshop.features.payment.data.entity;

import com.bookshop.features.order.data.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "PAYMENT")
public class PaymentEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

    @ManyToOne()
    @Enumerated(value = EnumType.STRING)
    @JoinColumn(name = "PAYMENT_ID", nullable = false)
    private PaymentMethodEntity paymentMethodEntity;

    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}

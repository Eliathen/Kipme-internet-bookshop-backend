package com.bookshop.features.payment.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PAYMENT_METHOD")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "METHOD")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private PaymentType type;

    @OneToMany(mappedBy = "paymentMethodEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PaymentEntity> payments;

    private BigDecimal price;
}

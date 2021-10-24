package com.bookshop.features.payment.entity;

import com.bookshop.features.order.entity.OrderEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;

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

    /*
    private PaymentType paymentType; ENUM
    private OrderEntity orderid; - relations
    private PaymentStatus paymentStatus; ENUM
     */

    public PaymentEntity() {
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}

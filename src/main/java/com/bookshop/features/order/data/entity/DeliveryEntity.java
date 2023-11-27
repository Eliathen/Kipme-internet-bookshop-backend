package com.bookshop.features.order.data.entity;

import com.bookshop.features.user.data.entity.AddressEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DELIVERY")
public class DeliveryEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;


    private BigDecimal deliveryPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private AddressEntity address;

    @OneToOne(mappedBy = "deliveryEntity", cascade = CascadeType.ALL)
    private OrderEntity order;

    @ManyToOne
    private DeliveryMethodEntity deliveryMethod;

}

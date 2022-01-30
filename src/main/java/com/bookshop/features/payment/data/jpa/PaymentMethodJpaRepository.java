package com.bookshop.features.payment.data.jpa;

import com.bookshop.features.payment.data.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodJpaRepository extends JpaRepository<PaymentMethodEntity, Integer> {
}

package com.bookshop.features.payment.data;

import com.bookshop.features.payment.data.entity.PaymentMethodEntity;
import com.bookshop.features.payment.data.jpa.PaymentJpaRepository;
import com.bookshop.features.payment.data.jpa.PaymentMethodJpaRepository;
import com.bookshop.features.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMethodJpaRepository paymentMethodJpaRepository;

    @Override
    public List<PaymentMethodEntity> getPaymentMethods() {
        return paymentMethodJpaRepository.findAll();
    }

    @Override
    public Optional<PaymentMethodEntity> getPaymentMethodById(Integer paymentMethodId) {
        return paymentMethodJpaRepository.findById(paymentMethodId);
    }
}

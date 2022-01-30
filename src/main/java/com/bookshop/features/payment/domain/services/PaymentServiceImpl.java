package com.bookshop.features.payment.domain.services;

import com.bookshop.features.payment.api.PaymentService;
import com.bookshop.features.payment.data.entity.PaymentMethodEntity;
import com.bookshop.features.payment.domain.repository.PaymentRepository;
import com.bookshop.features.payment.exception.PaymentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentMethodEntity> getPaymentMethods() {
        return paymentRepository.getPaymentMethods();
    }

    @Override
    public PaymentMethodEntity getPaymentMethodById(Integer paymentMethodId) {
        return paymentRepository.getPaymentMethodById(paymentMethodId).orElseThrow(PaymentNotFound::new);
    }
}

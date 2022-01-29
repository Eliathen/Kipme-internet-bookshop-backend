package com.bookshop.features.payment.domain.services;

import com.bookshop.features.payment.api.PaymentService;
import com.bookshop.features.payment.data.entity.PaymentMethodEntity;
import com.bookshop.features.payment.domain.repository.PaymentRepository;
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
}

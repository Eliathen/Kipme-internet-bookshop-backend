package com.bookshop.features.payment.api.controller;


import com.bookshop.features.payment.api.PaymentService;
import com.bookshop.features.payment.api.response.PaymentMethodResponse;
import com.bookshop.features.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/methods")
    public ResponseEntity<List<PaymentMethodResponse>> getPaymentsMethods() {
        return ResponseEntity.ok(
                paymentService.getPaymentMethods()
                        .stream()
                        .map(PaymentMapper::mapToPaymentMethodResponse)
                        .toList()
        );
    }
}

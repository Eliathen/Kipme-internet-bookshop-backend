package com.bookshop.features.order.api.controller;


import com.bookshop.features.order.api.response.DeliveryMethodResponse;
import com.bookshop.features.order.domain.service.port.OrderService;
import com.bookshop.features.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final OrderService orderService;

    @GetMapping("/methods")
    public ResponseEntity<List<DeliveryMethodResponse>> getDeliveryMethods() {
        return ResponseEntity.ok(orderService.getDeliveryMethods().stream().map(OrderMapper::mapToDeliveryMethodResponse).collect(Collectors.toList()));
    }
}

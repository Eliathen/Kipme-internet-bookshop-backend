package com.bookshop.features.order.api.controller;


import com.bookshop.features.order.api.request.OrderRequest;
import com.bookshop.features.order.api.response.OrderResponse;
import com.bookshop.features.order.domain.service.port.OrderService;
import com.bookshop.features.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Transactional
    @PostMapping
    public ResponseEntity<OrderResponse> makeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(OrderMapper.mapToOrderResponse(orderService.makeOrder(request)));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getUserOrders() {
        return ResponseEntity.ok(orderService.getUserOrders().stream().map(OrderMapper::mapToOrderResponse).collect(Collectors.toList()));
    }
}

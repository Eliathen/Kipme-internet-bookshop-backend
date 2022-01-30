package com.bookshop.features.order.domain.repository;


import com.bookshop.features.order.data.entity.OrderEntity;

public interface OrderRepository {

    OrderEntity createOrder(OrderEntity order);
}

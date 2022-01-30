package com.bookshop.features.order.domain.service.port;

import com.bookshop.features.order.api.request.OrderRequest;
import com.bookshop.features.order.data.entity.DeliveryMethodEntity;
import com.bookshop.features.order.data.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<DeliveryMethodEntity> getDeliveryMethods();

    OrderEntity makeOrder(OrderRequest request);

    List<OrderEntity> getUserOrders();
}

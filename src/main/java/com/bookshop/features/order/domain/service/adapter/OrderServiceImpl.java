package com.bookshop.features.order.domain.service.adapter;

import com.bookshop.features.order.data.entity.DeliveryMethodEntity;
import com.bookshop.features.order.domain.repository.DeliveryMethodRepository;
import com.bookshop.features.order.domain.repository.OrderRepository;
import com.bookshop.features.order.domain.service.port.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;

    @Override
    public List<DeliveryMethodEntity> getDeliveryMethods() {
        return deliveryMethodRepository.getDeliveryMethods();
    }
}

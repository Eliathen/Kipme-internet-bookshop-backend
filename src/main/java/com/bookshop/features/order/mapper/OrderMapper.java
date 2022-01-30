package com.bookshop.features.order.mapper;

import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.order.api.response.DeliveryDetailsResponse;
import com.bookshop.features.order.api.response.DeliveryMethodResponse;
import com.bookshop.features.order.api.response.OrderPositionResponse;
import com.bookshop.features.order.api.response.OrderResponse;
import com.bookshop.features.order.data.entity.DeliveryEntity;
import com.bookshop.features.order.data.entity.DeliveryMethodEntity;
import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.data.entity.OrderPositionEntity;
import com.bookshop.features.payment.mapper.PaymentMapper;

import java.util.stream.Collectors;

public class OrderMapper {


    public static DeliveryMethodResponse mapToDeliveryMethodResponse(DeliveryMethodEntity entity) {
        return DeliveryMethodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }

    public static DeliveryDetailsResponse mapToDeliveryDetailsResponse(DeliveryEntity entity) {
        return DeliveryDetailsResponse.builder()
                .address(AddressMapper.mapToAddressResponse(entity.getAddress()))
                .id(entity.getId())
                .deliveryMethod(mapToDeliveryMethodResponse(entity.getDeliveryMethod()))
                .build();
    }

    public static OrderPositionResponse mapToOrderPositionResponse(OrderPositionEntity entity) {
        return OrderPositionResponse.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .orderedBook(BookMapper.mapToBookBaseResponse(entity.getOrderedBook()))
                .build();
    }

    public static OrderResponse mapToOrderResponse(OrderEntity order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .fullPrice(order.getFullPrice())
                .payment(PaymentMapper.mapToPaymentDetailsResponse(order.getPayment()))
                .books(order.getOrderPositions().stream().map(OrderMapper::mapToOrderPositionResponse).collect(Collectors.toList()))
                .delivery(mapToDeliveryDetailsResponse(order.getDeliveryEntity()))
                .build();
    }
}

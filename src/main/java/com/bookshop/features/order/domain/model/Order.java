package com.bookshop.features.order.domain.model;


import com.bookshop.features.payment.domain.model.Payment;
import com.bookshop.features.user.domain.model.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.*;

@Builder
public class Order {


    private UUID id;

    private LocalDateTime orderDate;

    private User user;

    private Address address;

    private List<BookPosition> orderedBooks = new LinkedList<>();

    private Payment payment;

    private DeliveryType deliveryType;

    private OrderStatus orderStatus;

}

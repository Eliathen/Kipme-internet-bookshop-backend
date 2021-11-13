package com.bookshop.features.order.domain.model;


import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.order.data.entity.DeliveryTypeEntity;
import com.bookshop.features.order.data.entity.OrderStatusEntity;
import com.bookshop.features.payment.domain.model.Payment;
import com.bookshop.features.payment.entity.PaymentEntity;
import com.bookshop.features.user.domain.model.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

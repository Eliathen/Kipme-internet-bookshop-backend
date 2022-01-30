package com.bookshop.features.order.api.response;


import com.bookshop.features.order.data.entity.OrderStatus;
import com.bookshop.features.payment.api.response.PaymentDetailsResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {

    private UUID id;

    private LocalDateTime orderDate;

    private List<OrderPositionResponse> books;

    private PaymentDetailsResponse payment;

    private DeliveryDetailsResponse delivery;

    private OrderStatus orderStatus;

    private BigDecimal fullPrice;

    @JsonCreator
    public OrderResponse(@JsonProperty("id") UUID id,
                         @JsonProperty("date") LocalDateTime orderDate,
                         @JsonProperty("books") List<OrderPositionResponse> books,
                         @JsonProperty("payment_details") PaymentDetailsResponse payment,
                         @JsonProperty("delivery_details") DeliveryDetailsResponse delivery,
                         @JsonProperty("full_price") BigDecimal fullPrice,
                         @JsonProperty("status") OrderStatus orderStatus) {
        this.id = id;
        this.orderDate = orderDate;
        this.books = books;
        this.payment = payment;
        this.delivery = delivery;
        this.fullPrice = fullPrice;
        this.orderStatus = orderStatus;
    }
}

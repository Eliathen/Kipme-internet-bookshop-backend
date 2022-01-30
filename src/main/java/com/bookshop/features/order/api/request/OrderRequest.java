package com.bookshop.features.order.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequest {

    private Integer paymentMethodId;

    private Integer deliveryMethodId;

    private Long deliveryAddressId;

    @JsonCreator
    public OrderRequest(@JsonProperty(value = "payment_method_id", required = true) Integer paymentMethodId,
                        @JsonProperty(value = "delivery_method_id", required = true) Integer deliveryMethodId,
                        @JsonProperty(value = "delivery_address_id", required = true) Long deliveryAddressId
    ) {
        this.paymentMethodId = paymentMethodId;
        this.deliveryMethodId = deliveryMethodId;
        this.deliveryAddressId = deliveryAddressId;
    }
}

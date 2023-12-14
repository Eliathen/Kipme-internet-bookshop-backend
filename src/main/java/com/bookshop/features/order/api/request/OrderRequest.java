package com.bookshop.features.order.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull(message = "Provide payment method's id")
        @JsonProperty(value = "payment_method_id", required = true)
        Integer paymentMethodId,
        @NotNull(message = "Provide delivery method's id")
        @JsonProperty(value = "delivery_method_id", required = true)
        Integer deliveryMethodId,
        @NotNull(message = "Provide delivery address' id")
        @JsonProperty(value = "delivery_address_id", required = true)
        Long deliveryAddressId
) {

}

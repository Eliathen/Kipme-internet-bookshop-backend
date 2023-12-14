package com.bookshop.features.order.api.response;

import com.bookshop.features.user.api.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DeliveryDetailsResponse(
        @JsonProperty(value = "id")
        UUID id,
        @JsonProperty(value = "address")
        AddressResponse address,
        @JsonProperty(value = "delivery_method")
        DeliveryMethodResponse deliveryMethod
) {
}

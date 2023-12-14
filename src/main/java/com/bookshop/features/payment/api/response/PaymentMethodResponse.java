package com.bookshop.features.payment.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PaymentMethodResponse(
        @JsonProperty(value = "id")
        Integer id,
        @JsonProperty(value = "name")
        String name
) {

}

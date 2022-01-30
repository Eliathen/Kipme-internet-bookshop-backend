package com.bookshop.features.order.api.response;

import com.bookshop.features.user.api.response.AddressResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DeliveryDetailsResponse {

    private UUID id;

    private AddressResponse address;

    private DeliveryMethodResponse deliveryMethod;

    @JsonCreator
    public DeliveryDetailsResponse(@JsonProperty("id") UUID id,
                                   @JsonProperty("address") AddressResponse address,
                                   @JsonProperty("delivery_method") DeliveryMethodResponse deliveryMethod) {
        this.id = id;
        this.address = address;
        this.deliveryMethod = deliveryMethod;
    }
}

package com.bookshop.features.payment.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentMethodResponse {


    private Integer id;

    private String name;

    @JsonCreator
    public PaymentMethodResponse(@JsonProperty("id") Integer id,
                                 @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}

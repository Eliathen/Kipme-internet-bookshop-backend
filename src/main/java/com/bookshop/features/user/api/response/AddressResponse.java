package com.bookshop.features.user.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AddressResponse(

        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "voivodeship")
        String voivodeship,
        @JsonProperty(value = "postal_code")
        String postalCode,
        @JsonProperty(value = "city")
        String city,
        @JsonProperty(value = "street")
        String street,
        @JsonProperty(value = "building_number")
        String buildingNumber,
        @JsonProperty(value = "flat_number")
        String flatNumber
) {
}
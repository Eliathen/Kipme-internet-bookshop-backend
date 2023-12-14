package com.bookshop.features.user.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SaveUpdateAddressRequest(
        @NotBlank(message = "Provide voivodeship")
        @JsonProperty(value = "voivodeship", required = true)
        String voivodeship,
        @NotBlank(message = "Provide postal code")
        @JsonProperty(value = "postal_code", required = true)
        String postalCode,
        @NotBlank(message = "Provide city")
        @JsonProperty(value = "city", required = true)
        String city,
        @NotBlank(message = "Provide street")
        @JsonProperty(value = "street", required = true)
        String street,
        @NotBlank(message = "Provide building number")
        @JsonProperty(value = "building_number", required = true)
        String buildingNumber,
        @JsonProperty(value = "flat_number")
        String flatNumber
) {

}

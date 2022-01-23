package com.bookshop.features.user.api.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {

    private Long id;

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    @JsonCreator
    public AddressResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("voivodeship") String voivodeship,
            @JsonProperty("postal_code") String postalCode,
            @JsonProperty("city") String city,
            @JsonProperty("street") String street,
            @JsonProperty("building_number") String buildingNumber,
            @JsonProperty("flat_number") String flatNumber) {
        this.id = id;
        this.voivodeship = voivodeship;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
    }
}

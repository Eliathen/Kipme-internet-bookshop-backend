package com.bookshop.features.user.api.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUpdateAddressRequest {

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    @JsonCreator
    public SaveUpdateAddressRequest(
            @JsonProperty("voivodeship") String voivodeship,
            @JsonProperty("postal_code") String postalCode,
            @JsonProperty("city") String city,
            @JsonProperty("street") String street,
            @JsonProperty("building_number") String buildingNumber,
            @JsonProperty("flat_number") String flatNumber) {
        this.voivodeship = voivodeship;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
    }
}

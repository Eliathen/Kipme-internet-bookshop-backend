package com.bookshop.features.user.api.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveUpdateAddressRequest {

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    @JsonCreator
    public SaveUpdateAddressRequest(@JsonProperty(value = "voivodeship", required = true) String voivodeship,
                                    @JsonProperty(value = "postal_code", required = true) String postalCode,
                                    @JsonProperty(value = "city", required = true) String city,
                                    @JsonProperty(value = "street", required = true) String street,
                                    @JsonProperty(value = "building_number", required = true) String buildingNumber,
                                    @JsonProperty(value = "flat_number") String flatNumber) {
        this.voivodeship = voivodeship;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
    }
}

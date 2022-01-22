package com.bookshop.features.user.api.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveUpdateAddressRequest {

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

}

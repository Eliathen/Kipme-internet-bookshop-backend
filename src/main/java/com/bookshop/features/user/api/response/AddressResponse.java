package com.bookshop.features.user.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressResponse {

    private Long id;

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

}

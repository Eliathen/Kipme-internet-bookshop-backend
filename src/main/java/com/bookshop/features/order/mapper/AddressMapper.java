package com.bookshop.features.order.mapper;


import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.api.response.AddressResponse;
import com.bookshop.features.user.data.entity.AddressEntity;

public class AddressMapper {
    public static AddressEntity mapToAddressEntity(SaveUpdateAddressRequest request) {
        return AddressEntity.builder()
                .city(request.getCity())
                .buildingNumber(request.getBuildingNumber())
                .flatNumber(request.getFlatNumber())
                .postalCode(request.getPostalCode())
                .street(request.getStreet())
                .voivodeship(request.getVoivodeship())
                .build();
    }

    public static AddressResponse mapToAddressResponse(AddressEntity addressEntity){
        return AddressResponse.builder()
                .id(addressEntity.getId())
                .city(addressEntity.getCity())
                .buildingNumber(addressEntity.getBuildingNumber())
                .flatNumber(addressEntity.getFlatNumber())
                .postalCode(addressEntity.getPostalCode())
                .street(addressEntity.getStreet())
                .voivodeship(addressEntity.getVoivodeship())
                .build();
    }
}

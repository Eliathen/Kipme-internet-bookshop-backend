package com.bookshop.features.order.mapper;


import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.api.response.AddressResponse;
import com.bookshop.features.user.data.entity.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {
    public static AddressEntity mapToAddressEntity(SaveUpdateAddressRequest request) {
        return AddressEntity.builder()
                .city(request.city())
                .buildingNumber(request.buildingNumber())
                .flatNumber(request.flatNumber())
                .postalCode(request.postalCode())
                .street(request.street())
                .voivodeship(request.voivodeship())
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

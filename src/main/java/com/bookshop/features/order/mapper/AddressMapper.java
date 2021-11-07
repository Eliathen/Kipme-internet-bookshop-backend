package com.bookshop.features.order.mapper;


import com.bookshop.features.order.data.entity.AddressEntity;
import com.bookshop.features.order.domain.model.Address;

public class AddressMapper {
    public static AddressEntity mapToAddressEntity(Address address) {
        return AddressEntity.builder()
                .buildingNumber(address.getBuildingNumber())
                .city(address.getCity())
                .id(address.getId())
                .flatNumber(address.getFlatNumber())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .voivodeship(address.getVoivodeship())
                .build();

    }
}

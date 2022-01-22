package com.bookshop.features.user.domain;

import com.bookshop.features.user.data.entity.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    List<AddressEntity> getUserAddresses(Long userId);

    AddressEntity saveAddress(AddressEntity address);

    AddressEntity updateAddress(AddressEntity address);

    void removeAddressById(Long addressId);

    Optional<AddressEntity> getAddressById(Long addressId);
}

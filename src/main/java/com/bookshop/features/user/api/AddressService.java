package com.bookshop.features.user.api;

import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.data.entity.AddressEntity;

import java.util.List;

public interface AddressService {

    List<AddressEntity> getUserAddresses(Long userId);

    AddressEntity saveAddress(Long userId, SaveUpdateAddressRequest request);

    AddressEntity updateAddress(Long addressId, SaveUpdateAddressRequest request);

    void removeAddress(Long addressId);

    AddressEntity getAddressById(Long addressId);
}

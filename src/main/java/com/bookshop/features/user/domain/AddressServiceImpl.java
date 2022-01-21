package com.bookshop.features.user.domain;

import com.bookshop.core.utils.NotNullBeanUtils;
import com.bookshop.features.order.mapper.AddressMapper;
import com.bookshop.features.user.api.AddressService;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.data.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public List<AddressEntity> getUserAddresses(Long userId) {
        return addressRepository.getUserAddresses(userId);
    }

    @Override
    public AddressEntity saveAddress(Long userId, SaveUpdateAddressRequest request) {

        AddressEntity addressEntity = addressRepository.saveAddress(AddressMapper.mapToAddressEntity(request));
        var user = userService.getCurrentUser();
        if(user.getAddresses() == null){
            user.setAddresses(List.of(addressEntity));
        } else {
            user.getAddresses().add(addressEntity);
        }
        return addressEntity;
    }

    @Override
    public AddressEntity updateAddress(Long addressId, SaveUpdateAddressRequest request) {
        var address = getAddressById(addressId);
        NotNullBeanUtils.copyNonNullProperties(AddressMapper.mapToAddressEntity(request), address);
        return addressRepository.updateAddress(address);
    }

    @Override
    public void removeAddress(Long addressId) {
        addressRepository.removeAddressById(addressId);
    }

    @Override
    public AddressEntity getAddressById(Long addressId) {
        return null;
    }
}

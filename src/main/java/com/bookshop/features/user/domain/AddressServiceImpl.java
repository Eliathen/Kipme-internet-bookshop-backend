package com.bookshop.features.user.domain;

import com.bookshop.core.utils.NotNullBeanUtils;
import com.bookshop.features.order.mapper.AddressMapper;
import com.bookshop.features.user.api.AddressService;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.data.entity.AddressEntity;
import com.bookshop.features.user.exception.AddressNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public List<AddressEntity> getUserAddresses(Long userId) {
        return addressRepository.getUserAddresses(userId);
    }

    @Override
    public AddressEntity saveAddress(Long userId, SaveUpdateAddressRequest request) {
        AddressEntity address = AddressMapper.mapToAddressEntity(request);
        var user = userService.getCurrentUser();
        address.addUser(user);
        user.addAddress(address);
        return addressRepository.saveAddress(address);
    }

    @Override
    public AddressEntity updateAddress(Long addressId, SaveUpdateAddressRequest request) {
        var address = userService.getCurrentUser().getAddresses().stream()
                .filter(addressEntity -> Objects.equals(addressEntity.getId(), addressId))
                .findFirst().orElseThrow(AddressNotFound::new);
        NotNullBeanUtils.copyNonNullProperties(AddressMapper.mapToAddressEntity(request), address);
        return addressRepository.updateAddress(address);
    }

    @Override
    public void removeAddress(Long addressId) {
        addressRepository.removeAddressById(addressId);
    }

    @Override
    public AddressEntity getAddressById(Long addressId) {
        return userService.getCurrentUser().getAddresses()
                .stream().filter(address -> address.getId().equals(addressId))
                .findFirst().orElseThrow(AddressNotFound::new);
    }
}

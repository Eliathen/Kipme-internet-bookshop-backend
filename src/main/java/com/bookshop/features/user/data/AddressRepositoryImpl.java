package com.bookshop.features.user.data;

import com.bookshop.features.user.data.entity.AddressEntity;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.data.jpa.AddressJpaRepository;
import com.bookshop.features.user.domain.AddressRepository;
import com.bookshop.features.user.domain.UserRepository;
import com.bookshop.features.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;
    private final UserRepository userRepository;

    @Override
    public List<AddressEntity> getUserAddresses(Long userId) {
        UserEntity user = userRepository.getUserById(userId).orElseThrow(UserNotFoundException::new);
        return user.getAddresses();
    }


    @Override
    public AddressEntity saveAddress(AddressEntity address) {
        return addressJpaRepository.saveAndFlush(address);
    }

    @Override
    public AddressEntity updateAddress(AddressEntity address) {
        return addressJpaRepository.saveAndFlush(address);
    }

    @Override
    public void removeAddressById(Long addressId) {
        addressJpaRepository.findById(addressId).ifPresent(addressJpaRepository::delete);
    }

    @Override
    public Optional<AddressEntity> getAddressById(Long addressId) {
        return addressJpaRepository.findById(addressId);
    }
}

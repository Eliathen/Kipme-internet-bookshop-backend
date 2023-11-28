package com.bookshop.features.user.api.controller;


import com.bookshop.features.order.mapper.AddressMapper;
import com.bookshop.features.user.api.AddressService;
import com.bookshop.features.user.api.request.SaveUpdateAddressRequest;
import com.bookshop.features.user.api.response.AddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{userId}/addresses")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getUserAddresses(@PathVariable Long userId) {
        return new ResponseEntity<>(
                addressService.getUserAddresses(userId)
                        .stream()
                        .map(AddressMapper::mapToAddressResponse)
                        .toList(),
                HttpStatus.OK
        );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AddressResponse> saveAddress(@PathVariable Long userId,
                                                       @RequestBody @Valid SaveUpdateAddressRequest request) {
        return new ResponseEntity<>(
                AddressMapper.mapToAddressResponse(addressService.saveAddress(userId, request)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long userId,
                                                          @PathVariable Long addressId) {
        return ResponseEntity.ok(AddressMapper.mapToAddressResponse(addressService.getAddressById(addressId)));
    }

    @Transactional
    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long userId,
                                                         @PathVariable Long addressId,
                                                         @RequestBody @Valid SaveUpdateAddressRequest request) {
        return ResponseEntity.ok(AddressMapper.mapToAddressResponse(addressService.updateAddress(addressId, request)));
    }

    @Transactional
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long userId,
                                              @PathVariable Long addressId) {
        addressService.removeAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}

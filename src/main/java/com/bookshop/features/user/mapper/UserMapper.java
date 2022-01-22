package com.bookshop.features.user.mapper;

import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.api.response.BaseUserResponse;
import com.bookshop.features.user.api.response.UserResponse;
import com.bookshop.features.user.data.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapToUser(RegisterUserRequest registerUser) {
        return UserEntity.builder()
                .enabled(true)
                .email(registerUser.getEmail())
                .password(registerUser.getPassword())
                .name(registerUser.getName())
                .surname(registerUser.getSurname())
                .phoneNumber(registerUser.getPhoneNumber())
                .build();
    }

    public static UserResponse mapToUserResponse(UserEntity user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .surname(user.getSurname())
                .build();
    }

    public static BaseUserResponse mapToBaseUserResponse(UserEntity user) {
        return BaseUserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}

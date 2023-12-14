package com.bookshop.features.user.mapper;

import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.api.response.BaseUserResponse;
import com.bookshop.features.user.api.response.UserResponse;
import com.bookshop.features.user.data.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserEntity mapToUser(RegisterUserRequest registerUser) {
        return UserEntity.builder()
                .enabled(true)
                .email(registerUser.email())
                .password(registerUser.password())
                .name(registerUser.name())
                .surname(registerUser.surname())
                .phoneNumber(registerUser.phoneNumber())
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

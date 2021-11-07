package com.bookshop.features.user.mapper;

import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.opinion.mapper.OpinionMapper;
import com.bookshop.features.order.data.entity.AddressEntity;
import com.bookshop.features.order.mapper.AddressMapper;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.api.response.UserResponse;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.domain.model.User;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .opinions(
                        (user.getOpinions() != null) ?
                                user.getOpinions().stream().map(OpinionMapper::mapToOpinionEntity).collect(Collectors.toSet()) :
                                new HashSet<OpinionEntity>()
                )
                .addresses((user.getAddresses() != null) ?
                        user.getAddresses().stream().map(AddressMapper::mapToAddressEntity).collect(Collectors.toSet()) :
                        new HashSet<AddressEntity>())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User mapToUser(UserEntity userEntity) {
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    public static User mapToUser(RegisterUserRequest registerUser) {
        return User.builder()
                .enabled(true)
                .email(registerUser.getEmail())
                .password(registerUser.getPassword())
                .name(registerUser.getName())
                .surname(registerUser.getSurname())
                .phoneNumber(registerUser.getPhoneNumber())
                .build();
    }

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .surname(user.getSurname())
                .build();
    }
}

package com.bookshop.features.user.domain;

import com.bookshop.features.user.data.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity saveUser(UserEntity user);

    Optional<UserEntity> getUserById(Long id);

    Optional<UserEntity> getUserByEmail(String email);

}

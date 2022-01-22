package com.bookshop.features.user.api;


import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.data.entity.UserEntity;

public interface UserService {

    UserEntity register(RegisterUserRequest user);

    UserEntity getUserByEmail(String email);

    JwtAuthenticationResponse getJwt(LoginRequest request);

    UserEntity getCurrentUser();

    UserEntity getUserById(Long userId);
}

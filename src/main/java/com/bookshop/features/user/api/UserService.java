package com.bookshop.features.user.api;


import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.domain.model.User;

public interface UserService {

    User register(User user);

    User getUserByEmail(String email);

    JwtAuthenticationResponse getJwt(LoginRequest request);

    Long getCurrentUserId();

    User getUserById(Long userId);
}

package com.bookshop.features.user.domain;

import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.core.security.TokenProvider;
import com.bookshop.core.security.UserDetailsServiceImpl;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.data.entity.UserRole;
import com.bookshop.features.user.exception.UserNotFoundException;
import com.bookshop.features.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenProvider tokenProvider;

    @Override
    public UserEntity register(RegisterUserRequest user) {
        UserEntity newUser = UserMapper.mapToUser(user);
        newUser.setRole(UserRole.CLIENT);
        newUser.setPassword(passwordEncoder.encode(String.valueOf(newUser.getPassword())).toCharArray());
        validateUserEmail(user.getEmail());
        return userRepository.saveUser(newUser);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public JwtAuthenticationResponse getJwt(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        String.valueOf(loginRequest.getPassword())
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public UserEntity getCurrentUser() {
        return getUserById(userDetailsService.currentUser().getId());
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.getUserById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void validateUserEmail(String email) {
        EmailValidator.validate(email);
    }
}

package com.bookshop.features.user.domain;

import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.core.security.TokenProvider;
import com.bookshop.core.security.UserDetailsServiceImpl;
import com.bookshop.core.security.UserRole;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.exception.UserAlreadyExists;
import com.bookshop.features.user.exception.UserNotFoundException;
import com.bookshop.features.user.mapper.UserMapper;
import com.bookshop.features.user.validators.EmailValidator;
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
        validateUser(user);
        UserEntity newUser = UserMapper.mapToUser(user);
        newUser.setRole(UserRole.CLIENT);
        newUser.setPassword(passwordEncoder.encode(String.valueOf(newUser.getPassword())).toCharArray());
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
                        loginRequest.email(),
                        String.valueOf(loginRequest.password())
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

    @Override
    public UserEntity registerAdmin(RegisterUserRequest request) {
        validateUser(request);
        UserEntity newUser = UserMapper.mapToUser(request);
        newUser.setRole(UserRole.ADMIN);
        newUser.setPassword(passwordEncoder.encode(String.valueOf(newUser.getPassword())).toCharArray());
        return userRepository.saveUser(newUser);
    }

    @Override
    public void removeAccount() {
        var user = getCurrentUser();
        user.setEnabled(false);
        userRepository.saveUser(user);
    }

    private void validateUser(RegisterUserRequest userRequest) {
        EmailValidator.validate(userRequest.email());
        userRepository.getUserByEmail(userRequest.email()).ifPresent(user -> {
            throw new UserAlreadyExists();
        });
    }

}

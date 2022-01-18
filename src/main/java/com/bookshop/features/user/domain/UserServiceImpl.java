package com.bookshop.features.user.domain;

import com.bookshop.core.security.JwtAuthenticationResponse;
import com.bookshop.core.security.TokenProvider;
import com.bookshop.core.security.UserDetailsServiceImpl;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.data.entity.UserRole;
import com.bookshop.features.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public User register(User user) {
        user.setRole(UserRole.CLIENT);
        user.setPassword(passwordEncoder.encode(String.valueOf(user.getPassword())).toCharArray());
        return userRepository.saveUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
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
    public Long getCurrentUserId() {
        return userDetailsService.currentUser().getId();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
}

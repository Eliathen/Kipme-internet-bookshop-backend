package com.bookshop.features.user.api.controller;

import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.api.response.LoginResponse;
import com.bookshop.features.user.api.response.UserResponse;
import com.bookshop.features.user.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        return new ResponseEntity<>(
                UserMapper.mapToUserResponse(userService.register(request)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return new ResponseEntity<>(
                LoginResponse.of(
                        userService.getUserByEmail(request.email()), userService.getJwt(request)),
                HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("admins/signup")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody @Valid RegisterUserRequest request) {
        return new ResponseEntity<>(
                UserMapper.mapToUserResponse(userService.registerAdmin(request)),
                HttpStatus.CREATED
        );
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> removeAccount() {
        userService.removeAccount();
        return ResponseEntity.noContent().build();
    }
}

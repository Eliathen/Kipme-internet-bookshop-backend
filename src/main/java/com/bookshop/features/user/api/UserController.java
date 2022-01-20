package com.bookshop.features.user.api;

import com.bookshop.features.user.api.request.LoginRequest;
import com.bookshop.features.user.api.request.RegisterUserRequest;
import com.bookshop.features.user.api.response.LoginResponse;
import com.bookshop.features.user.api.response.UserResponse;
import com.bookshop.features.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        return new ResponseEntity<>(
                UserMapper.mapToUserResponse(userService.register(UserMapper.mapToUser(request))),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return new ResponseEntity<>(
                LoginResponse.of(
                        userService.getUserByEmail(request.getEmail()), userService.getJwt(request)),
                HttpStatus.OK);
    }

    //TODO adding address to user
}

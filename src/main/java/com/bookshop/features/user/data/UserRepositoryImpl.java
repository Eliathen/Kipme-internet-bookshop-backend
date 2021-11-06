package com.bookshop.features.user.data;

import com.bookshop.features.user.data.jpa.UserRepositoryJpa;
import com.bookshop.features.user.exception.UserNotFoundException;
import com.bookshop.features.user.mapper.UserMapper;
import com.bookshop.features.user.domain.UserRepository;
import com.bookshop.features.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepository;

    @Override
    public User saveUser(User user) {
        return UserMapper.mapToUser(userRepository.saveAndFlush(UserMapper.mapToUserEntity(user)));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::mapToUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::mapToUser).orElseThrow(UserNotFoundException::new);
    }

}

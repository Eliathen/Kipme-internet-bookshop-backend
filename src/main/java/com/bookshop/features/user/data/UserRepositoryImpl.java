package com.bookshop.features.user.data;

import com.bookshop.features.user.data.jpa.UserRepositoryJpa;
import com.bookshop.features.user.domain.UserRepository;
import com.bookshop.features.user.domain.model.User;
import com.bookshop.features.user.exception.InvalidEmailOrPassword;
import com.bookshop.features.user.exception.UserAlreadyExists;
import com.bookshop.features.user.exception.UserNotFoundException;
import com.bookshop.features.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepository;

    @Override
    public User saveUser(User user) {
        var result = userRepository.findByEmail(user.getEmail());
        if (result.isPresent()) {
            throw new UserAlreadyExists(user.getEmail());
        }
        return UserMapper.mapToUser(userRepository.saveAndFlush(UserMapper.mapToUserEntity(user)));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::mapToUser).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::mapToUser).orElseThrow(InvalidEmailOrPassword::new);
    }

}

package com.bookshop.features.user.data;

import com.bookshop.features.user.data.entity.UserEntity;
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
    public UserEntity saveUser(UserEntity user) {
        return userRepository.saveAndFlush(user);

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}

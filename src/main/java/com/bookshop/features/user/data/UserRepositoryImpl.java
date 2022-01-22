package com.bookshop.features.user.data;

import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.data.jpa.UserJpaRepository;
import com.bookshop.features.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

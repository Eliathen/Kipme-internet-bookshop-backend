package com.bookshop.features.user.domain;

import com.bookshop.features.user.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository {

    User saveUser(User user);

    User getUserById(Long id);

    User getUserByEmail(String email);

}

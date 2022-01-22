package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, Integer> {

    Optional<AuthorEntity> findFirstByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);

    List<AuthorEntity> findAuthorEntityByNameOrSurname(String name, String surname);
}

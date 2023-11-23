package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.base.MariaDbContainerBaseTest;
import com.bookshop.features.book.data.entity.AuthorEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
class AuthorJpaRepositoryTest extends MariaDbContainerBaseTest {


    @Autowired
    AuthorJpaRepository sut;

    @BeforeEach
    void setUp() {
        List<AuthorEntity> authors = List.of(
                AuthorEntity.builder().name("Peter").surname("Brett").build(),
                AuthorEntity.builder().name("Brandon").surname("Sanderson").build()

        );
        sut.saveAll(authors);
    }

    @Test
    void shouldReturnAllLanguages() {
        List<AuthorEntity> result = sut.findAll();

        assertThat(result.size()).isNotZero();
    }

    @Test
    @Rollback
    void shouldSaveLanguage() {
        AuthorEntity author = AuthorEntity.builder().name("John Ronald Reuel").surname("Tolkien").build();
        AuthorEntity saved = sut.save(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void shouldReturnAuthorWhenGivenNameAndSurnameLowerCase() {
        Optional<AuthorEntity> author = sut.findFirstByNameIgnoreCaseAndSurnameIgnoreCase("peter", "brett");

        assertThat(author).isPresent();
        assertThat(author.get().getName()).isEqualTo("Peter");
        assertThat(author.get().getSurname()).isEqualTo("Brett");
    }

    @Test
    @Rollback
    void shouldReturnAuthorsWhenGivenSurname() {
        sut.save(
                AuthorEntity.builder().name("Peter").surname("Brett").build()
        );
        List<AuthorEntity> authors = sut.findAuthorEntityByNameOrSurname("Peter", "Brett");

        assertThat(authors).hasSizeGreaterThan(1);
    }

    @Test
    void shouldReturnLanguageWhenGivenValidId() {
        Integer authorId = sut.findAll().stream().findFirst().get().getId();
        Optional<AuthorEntity> entity = sut.findById(authorId);

        Assertions.assertThat(entity).isNotNull();
        assertThat(entity.get().getId()).isEqualTo(authorId);
    }

    @Test
    void shouldNotReturnAuthorWhenGivenInvalidId() {
        Optional<AuthorEntity> entity = sut.findById(999999);

        Assertions.assertThat(entity).isNotPresent();
    }

}
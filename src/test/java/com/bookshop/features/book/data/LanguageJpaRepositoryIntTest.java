package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.data.jpa.LanguageJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@DataJpaTest
class LanguageJpaRepositoryIntTest {

    @Autowired
    private LanguageJpaRepository jpaRepository;
    @Container
    private static final MariaDBContainer<?> mariaDB = new MariaDBContainer<>().withUsername("kipme").withPassword("kipme-secret");
    private List<LanguageEntity> languages;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        mariaDB.start();
        registry.add("spring.datasource.url", mariaDB::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDB::getUsername);
        registry.add("spring.datasource.password", mariaDB::getPassword);
    }

    @BeforeEach
    void setUp() {
        languages = List.of(
                new LanguageEntity(1, "English", Collections.emptySet()),
                new LanguageEntity(2, "Polish", Collections.emptySet())
        );
        jpaRepository.saveAll(languages);
    }

    @Test
    void connectionEstablished() {
        assertThat(mariaDB.isCreated()).isTrue();
        assertThat(mariaDB.isRunning()).isTrue();
    }

    @Test
    void shouldReturnAllLanguages() {
        List<LanguageEntity> result = jpaRepository.findAll();
        assertThat(result.size()).isNotZero();
    }

    @Test
    @Rollback
    void shouldSaveLanguage() {
        LanguageEntity language = LanguageEntity.builder().name("Spanish").build();
        LanguageEntity saved = jpaRepository.save(language);
        assertThat(saved).isNotNull();
    }

    @Test
    void shouldReturnLanguageWhenGivenValidId() {
        Integer languageId = jpaRepository.findAll().stream().findFirst().get().getId();
        Optional<LanguageEntity> entity = jpaRepository.findById(languageId);

        Assertions.assertThat(entity).isNotNull();
        assertThat(entity.get().getId()).isEqualTo(languageId);
    }

    @Test
    void shouldReturnEmptyOptionalLanguageWhenGivenInvalidId() {
        Optional<LanguageEntity> entity = jpaRepository.findById(999999);

        Assertions.assertThat(entity.isPresent()).isFalse();
    }
}
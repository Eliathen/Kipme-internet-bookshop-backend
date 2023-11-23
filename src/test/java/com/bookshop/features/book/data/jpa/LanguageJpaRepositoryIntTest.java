package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.base.MariaDbContainerBaseTest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class LanguageJpaRepositoryIntTest extends MariaDbContainerBaseTest {

    @Autowired
    private LanguageJpaRepository jpaRepository;


    @BeforeEach
    void setUp() {
        List<LanguageEntity> languages = List.of(
                new LanguageEntity(1, "English", Collections.emptySet()),
                new LanguageEntity(2, "Polish", Collections.emptySet())
        );
        jpaRepository.saveAll(languages);
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

        Assertions.assertThat(entity).isPresent();
        assertThat(entity.get().getId()).isEqualTo(languageId);
    }

    @Test
    void shouldNotReturnLanguageWhenGivenInvalidId() {
        Optional<LanguageEntity> entity = jpaRepository.findById(999999);

        Assertions.assertThat(entity).isNotPresent();
    }
}
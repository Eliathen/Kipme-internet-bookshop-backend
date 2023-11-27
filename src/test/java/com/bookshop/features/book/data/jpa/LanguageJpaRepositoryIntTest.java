package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.base.MariaDbContainerBaseTest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LanguageJpaRepositoryIntTest extends MariaDbContainerBaseTest {

    @Autowired
    private LanguageJpaRepository sut;

    @BeforeEach
    void setUp() {
        List<LanguageEntity> languages = List.of(
                getEnglishLanguage(),
                getPolishLanguage()
        );
        sut.saveAll(languages);
    }

    @Test
    void shouldReturnAllLanguages() {
        List<LanguageEntity> result = sut.findAll();

        assertThat(result.size()).isNotZero();
    }

    @Test
    void shouldSaveLanguage() {
        LanguageEntity language = getSpanishLanguage();

        LanguageEntity saved = sut.save(language);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void shouldReturnLanguageWhenGivenValidId() {
        Integer languageId = sut.save(getSpanishLanguage()).getId();

        Optional<LanguageEntity> entity = sut.findById(languageId);

        assertThat(entity).isPresent();
        assertThat(entity.get().getId()).isEqualTo(languageId);
    }

    @Test
    void shouldNotReturnLanguageWhenGivenInvalidId() {
        Optional<LanguageEntity> entity = sut.findById(999999);

        assertThat(entity).isNotPresent();
    }

    @NotNull
    private static LanguageEntity getPolishLanguage() {
        return new LanguageEntity(2, "Polish", Collections.emptySet());
    }

    @NotNull
    private static LanguageEntity getEnglishLanguage() {
        return new LanguageEntity(1, "English", Collections.emptySet());
    }

    private static LanguageEntity getSpanishLanguage() {
        return LanguageEntity.builder().name("Spanish").build();
    }
}
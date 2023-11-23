package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.data.jpa.LanguageJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class LanguageRepositoryImplTest {
    private LanguageRepositoryImpl sut;
    private final LanguageJpaRepository jpa = Mockito.mock(LanguageJpaRepository.class);
    private static List<LanguageEntity> languages;

    @BeforeEach
    void setUp() {
        languages = List.of(
                new LanguageEntity(1, "English", Collections.emptySet()),
                new LanguageEntity(2, "Polish", Collections.emptySet())
        );
        sut = new LanguageRepositoryImpl(jpa);
    }

    @Test
    void shouldFindAllLanguages() {
        when(jpa.findAll()).thenReturn(languages);

        List<LanguageEntity> result = sut.getLanguages();

        assertThat(result).hasSize(languages.size());
    }

    @Test
    void shouldFindLanguageWhenGivenValidId() {
        LanguageEntity language = new LanguageEntity(1, "English", Collections.emptySet());
        when(jpa.findById(language.getId())).thenReturn(Optional.of(language));

        Optional<LanguageEntity> result = sut.getLanguageById(language.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(language.getId());
    }

    @Test
    void shouldReturnNullOptionalWhenGivenInvalidId() {
        when(jpa.findById(999)).thenReturn(Optional.empty());

        Optional<LanguageEntity> result = sut.getLanguageById(999);

        assertThat(result).isNotPresent();
    }

    @Test
    void shouldCreateNewLanguage() {
        //given
        LanguageEntity entity = new LanguageEntity(3, "Spanish", Set.of());
        when(jpa.saveAndFlush(entity)).thenReturn(entity);
        //when
        LanguageEntity result = sut.saveLanguage(entity);
        // then
        assertThat(result.getId()).isEqualTo(entity.getId());
    }

}

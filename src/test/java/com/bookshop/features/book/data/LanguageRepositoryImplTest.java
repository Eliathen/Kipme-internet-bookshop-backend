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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        //given
        when(jpa.findAll()).thenReturn(languages);
        //when
        List<LanguageEntity> result = sut.getLanguages();

        assertEquals(result.size(), languages.size());
    }

    @Test
    void shouldFindLanguageWhenGivenValidId() {
        //given
        LanguageEntity language = new LanguageEntity(1, "English", Collections.emptySet());
        when(jpa.findById(any())).thenReturn(Optional.of(language));
        //when
        Optional<LanguageEntity> result = sut.getLanguageById(language.getId());
        //then
        assertTrue(result.isPresent());
        assertEquals(language.getId(), result.get().getId());


    }

    @Test
    void shouldReturnNullOptionalWhenGivenInvalidId() {
        //given
        when(jpa.findById(999)).thenReturn(Optional.empty());
        //when
        Optional<LanguageEntity> result = sut.getLanguageById(999);
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void shouldCreateNewLanguage() {
        //given
        LanguageEntity entity = new LanguageEntity(3, "Spanish", Set.of());
        when(jpa.saveAndFlush(entity)).thenReturn(entity);
        //when
        LanguageEntity result = sut.saveLanguage(entity);
        // then
        assertEquals(entity.getId(), result.getId());
    }

}
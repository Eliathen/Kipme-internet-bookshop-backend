package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.LanguageRepositoryImpl;
import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.domain.repository.LanguageRepository;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.exception.LanguageNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LanguageServiceImplTest {

    private LanguageService sut;

    private final LanguageRepository languageRepository = Mockito.mock(LanguageRepositoryImpl.class);

    private List<LanguageEntity> languages;

    @BeforeEach
    void setUp() {
        languages = List.of(
                new LanguageEntity(1, "Polish", Collections.emptySet()),
                new LanguageEntity(2, "English", Collections.emptySet())
        );
        sut = new LanguageServiceImpl(languageRepository);
    }

    // get all languages
    @Test
    void shouldFindAllLanguages() {
        //given
        when(languageRepository.getLanguages()).thenReturn(languages);

        //when
        List<LanguageEntity> result = sut.getLanguages();

        assertEquals(languages.size(), result.size());
    }

    @Test
    void shouldFindLanguageWhenGivenValidId() {
        //given
        Optional<LanguageEntity> language = languages.stream().findFirst();
        when(languageRepository.getLanguageById(any())).thenReturn(language);

        //when
        LanguageEntity result = sut.getLanguage(1);
        //then
        assertEquals(language.get().getId(), result.getId());
    }

    @Test
    void shouldThrowExceptionLanguageNotFoundWhenGivenInvalidId() {
        //given
        when(languageRepository.getLanguageById(999)).thenThrow(LanguageNotFound.class);

        //when & then
        assertThrows(LanguageNotFound.class, () -> sut.getLanguage(999));
    }

    @Test
    void shouldCreateNewLanguage() {
        //given
        SaveLanguageRequest request = new SaveLanguageRequest("Spanish");
        LanguageEntity entity = LanguageEntity.builder().name("Spanish").build();
        when(languageRepository.saveLanguage(any())).thenReturn(entity);

        //when
        LanguageEntity result = sut.saveLanguage(request);
        // then
        assertEquals(entity.getName(), result.getName());
    }
}
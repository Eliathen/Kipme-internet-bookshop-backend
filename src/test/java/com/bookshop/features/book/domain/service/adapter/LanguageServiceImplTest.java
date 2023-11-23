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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void shouldFindAllLanguages() {
        when(languageRepository.getLanguages()).thenReturn(languages);

        List<LanguageEntity> result = sut.getLanguages();

        assertThat(languages).hasSize(result.size());
    }

    @Test
    void shouldFindLanguageWhenGivenValidId() {
        Optional<LanguageEntity> language = languages.stream().findFirst();
        Integer languageId = language.get().getId();

        when(languageRepository.getLanguageById(any())).thenReturn(language);

        LanguageEntity result = sut.getLanguage(1);

        assertThat(result.getId()).isEqualTo(languageId);
    }

    @Test
    void shouldThrowExceptionLanguageNotFoundWhenGivenInvalidId() {
        when(languageRepository.getLanguageById(999)).thenThrow(LanguageNotFound.class);

        assertThatThrownBy(() -> sut.getLanguage(999)).isInstanceOf(LanguageNotFound.class);
    }

    @Test
    void shouldCreateNewLanguage() {
        SaveLanguageRequest request = new SaveLanguageRequest("Spanish");
        LanguageEntity entity = LanguageEntity.builder().name("Spanish").build();
        when(languageRepository.saveLanguage(any())).thenReturn(entity);

        LanguageEntity result = sut.saveLanguage(request);

        assertThat(result.getName()).isEqualTo(entity.getName());
    }
}
package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.JpaLanguageRepository;
import com.bookshop.features.book.domain.model.Language;
import com.bookshop.features.book.domain.repository.LanguageRepository;
import com.bookshop.features.book.exception.LanguageNotFound;
import com.bookshop.features.book.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LanguageRepositoryImpl implements LanguageRepository {

    private final JpaLanguageRepository jpa;

    @Override
    public List<Language> getLanguages() {
        return jpa.findAll().stream().map(LanguageMapper::mapToLanguage).collect(Collectors.toList());
    }

    @Override
    public Language saveLanguage(Language language) {
        return LanguageMapper.mapToLanguage(jpa.saveAndFlush(LanguageMapper.mapToLanguageEntity(language)));
    }

    @Override
    public Language getLanguageById(Integer id) {
        return LanguageMapper.mapToLanguage(jpa.findById(id).orElseThrow(() -> new LanguageNotFound(id)));
    }
}

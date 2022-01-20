package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.LanguageJpaRepository;
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

    private final LanguageJpaRepository jpa;

    @Override
    public List<Language> getLanguages() {
        return jpa.findAll().stream().map(LanguageMapper::mapLanguageEntityToLanguage).collect(Collectors.toList());
    }

    @Override
    public Language saveLanguage(Language language) {
        return LanguageMapper.mapLanguageEntityToLanguage(jpa.saveAndFlush(LanguageMapper.mapLanguageToLanguageEntity(language)));
    }

    @Override
    public Language getLanguageById(Integer id) {
        return LanguageMapper.mapLanguageEntityToLanguage(jpa.findById(id).orElseThrow(() -> new LanguageNotFound(id)));
    }
}

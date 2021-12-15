package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.domain.model.Language;
import com.bookshop.features.book.domain.repository.LanguageRepository;
import com.bookshop.features.book.domain.service.port.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    @Override
    public List<Language> getLanguages() {
        return repository.getLanguages();
    }

    @Override
    public Language saveLanguage(Language language) {
        return repository.saveLanguage(language);
    }

    @Override
    public Language getLanguage(Integer id) {
        return repository.getLanguageById(id);
    }
}

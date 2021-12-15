package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.domain.model.Language;

import java.util.List;

public interface LanguageService {


    List<Language> getLanguages();

    Language saveLanguage(Language language);

    Language getLanguage(Integer id);
}

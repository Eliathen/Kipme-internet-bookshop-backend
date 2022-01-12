package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.domain.model.Language;

import java.util.List;

public interface LanguageRepository {

    List<Language> getLanguages();

    Language saveLanguage(Language language);

    Language getLanguageById(Integer id);
}

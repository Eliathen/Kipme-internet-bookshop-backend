package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.LanguageEntity;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository {

    List<LanguageEntity> getLanguages();

    LanguageEntity saveLanguage(LanguageEntity language);

    Optional<LanguageEntity> getLanguageById(Integer id);
}

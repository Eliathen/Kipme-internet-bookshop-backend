package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.entity.LanguageEntity;

import java.util.List;

public interface LanguageService {


    List<LanguageEntity> getLanguages();

    LanguageEntity saveLanguage(SaveLanguageRequest language);

    LanguageEntity getLanguage(Integer id);
}

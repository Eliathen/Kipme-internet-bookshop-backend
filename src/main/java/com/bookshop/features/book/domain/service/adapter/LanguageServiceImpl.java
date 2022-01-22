package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.data.entity.LanguageEntity;
import com.bookshop.features.book.domain.repository.LanguageRepository;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.exception.LanguageNotFound;
import com.bookshop.features.book.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    @Override
    public List<LanguageEntity> getLanguages() {
        return repository.getLanguages();
    }

    @Override
    public LanguageEntity saveLanguage(SaveLanguageRequest request) {
        return repository.saveLanguage(LanguageMapper.mapToLanguageEntity(request));
    }

    @Override
    public LanguageEntity getLanguage(Integer id) {
        return repository.getLanguageById(id).orElseThrow(() -> new LanguageNotFound(id));
    }
}

package com.bookshop.features.book.api.controller;

import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.mapper.LanguageMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<LanguageResponse> getLanguages() {
        return service.getLanguages()
                .stream()
                .map(LanguageMapper::mapToLanguageResponse)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LanguageResponse getLanguage(@PathVariable Integer id) {
        return LanguageMapper.mapToLanguageResponse(service.getLanguage(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LanguageResponse saveLanguage(@RequestBody @Valid SaveLanguageRequest request) {
        return LanguageMapper.mapToLanguageResponse(service.saveLanguage(request));
    }
}

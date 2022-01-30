package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService service;

    @GetMapping
    public ResponseEntity<List<LanguageResponse>> getLanguages() {
        return ResponseEntity.ok(service.getLanguages().stream().map(LanguageMapper::mapToLanguageResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageResponse> getLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(LanguageMapper.mapToLanguageResponse(service.getLanguage(id)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity<LanguageResponse> saveLanguage(@RequestBody SaveLanguageRequest request) {
        return new ResponseEntity<>(LanguageMapper.mapToLanguageResponse(service.saveLanguage(request)), HttpStatus.CREATED);
    }
}

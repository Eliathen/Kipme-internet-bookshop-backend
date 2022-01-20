package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SaveLanguageRequest;
import com.bookshop.features.book.api.response.LanguageResponse;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(service.getLanguages().stream().map(LanguageMapper::mapLanguageToLanguageResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageResponse> getLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(LanguageMapper.mapLanguageToLanguageResponse(service.getLanguage(id)));
    }


    @PostMapping
    public ResponseEntity<LanguageResponse> saveLanguage(@RequestBody SaveLanguageRequest request){
        return new ResponseEntity<>(LanguageMapper.mapLanguageToLanguageResponse(service.saveLanguage(LanguageMapper.mapToLanguage(request))), HttpStatus.CREATED);
    }
}

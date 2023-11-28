package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.mapper.PublisherMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService service;


    @GetMapping
    public ResponseEntity<List<PublisherResponse>> getPublishers() {
        return ResponseEntity.ok(
                service.getPublishers()
                        .stream()
                        .map(PublisherMapper::mapToPublisherResponse)
                        .toList()
        );
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<PublisherResponse> savePublisher(@RequestBody @Valid SavePublisherRequest request) {
        return new ResponseEntity<>(
                PublisherMapper.mapToPublisherResponse(
                        service.savePublisher(PublisherMapper.mapToPublisher(request))),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublisher(@PathVariable Integer id) {
        return ResponseEntity.ok(
                PublisherMapper.mapToPublisherResponse(service.getPublisher(id))
        );
    }

}

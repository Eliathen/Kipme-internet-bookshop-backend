package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService service;


    @GetMapping
    public ResponseEntity<List<PublisherResponse>> getPublishers() {
        return ResponseEntity.ok(service.getPublishers().stream().map(PublisherMapper::mapPublisherToPublisherResponse).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> savePublisher(@RequestBody SavePublisherRequest request) {
        return new ResponseEntity<>(PublisherMapper.mapPublisherToPublisherResponse(service.savePublisher(PublisherMapper.mapSavePublisherRequestToPublisher(request))), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponse> getPublisher(@PathVariable Integer id) {
        return ResponseEntity.ok(
                PublisherMapper.mapPublisherToPublisherResponse(service.getPublisher(id))
        );
    }

}

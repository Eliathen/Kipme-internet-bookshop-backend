package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SavePublisherRequest;
import com.bookshop.features.book.api.response.PublisherResponse;
import com.bookshop.features.book.api.service.PublisherService;
import com.bookshop.features.book.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<PublisherResponse>> getPublishers(){
        return ResponseEntity.ok(service.getPublishers().stream().map(PublisherMapper::mapToPublisherResponse).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> savePublisher(@RequestBody SavePublisherRequest request){
        return ResponseEntity.ok(PublisherMapper.mapToPublisherResponse(service.savePublisher(PublisherMapper.mapToPublisher(request))));
    }

}

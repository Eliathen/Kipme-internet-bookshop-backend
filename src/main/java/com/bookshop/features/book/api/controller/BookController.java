package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Transactional
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveBook(@ModelAttribute SaveBookRequest request, MultipartFile cover) throws IOException {
        BookMapper.mapToBookResponse(bookService.saveBook(request, cover));
        return ResponseEntity.noContent().build();
    }
}

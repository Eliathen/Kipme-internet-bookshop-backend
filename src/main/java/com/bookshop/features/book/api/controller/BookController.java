package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.domain.model.Cover;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.opinion.mapper.OpinionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Transactional
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> saveBook(@ModelAttribute SaveBookRequest request, @NotNull MultipartFile cover) throws IOException {
        return ResponseEntity.ok(BookMapper.mapToBookResponse(bookService.saveBook(request, cover)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id){
        return ResponseEntity.ok(BookMapper.mapToBookResponse(bookService.getBookById(id)));
    }

    @GetMapping("/{bookId}/cover")
    public ResponseEntity<byte[]> getCoverByBookId(@PathVariable Long bookId) {
        Cover cover = bookService.getCoverByBookId(bookId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, cover.getType())
                .body(cover.getData());
    }

    @Transactional
    @PostMapping("/{bookId}/opinions")
    public ResponseEntity<Void> saveOpinion(@PathVariable Long bookId, @RequestBody AddOpinionRequest request) {
        bookService.saveOpinion(bookId, OpinionMapper.mapToOpinion(request));
        return ResponseEntity.noContent().build();
    }
}

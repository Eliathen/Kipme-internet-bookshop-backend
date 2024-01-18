package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.request.AddRemoveBookFavouriteRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.api.response.BookBaseResponse;
import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.CoverEntity;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.mapper.BookMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> saveBook(@RequestPart("request") @Valid SaveBookRequest request,
                                         @RequestPart("cover") MultipartFile cover) throws IOException {
        BookEntity result = bookService.saveBook(request, cover);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + result.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(BookMapper.mapToBookResponse(bookService.getBookById(id)));
    }

    @GetMapping("/{bookId}/cover")
    public ResponseEntity<byte[]> getCoverByBookId(@PathVariable Long bookId) {
        CoverEntity cover = bookService.getCoverByBookId(bookId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, cover.getType())
                .body(cover.getData());
    }

    @PostMapping("/{bookId}/opinions")
    public ResponseEntity<Void> saveOpinion(@PathVariable Long bookId,
                                            @RequestBody @Valid AddOpinionRequest request) {
        bookService.saveOpinion(bookId, request);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{bookId}/opinions/{opinionId}")
    public ResponseEntity<Void> removeOpinion(@PathVariable("bookId") Long bookId,
                                              @PathVariable("opinionId") Integer opinionId) {
        bookService.removeOpinion(bookId, opinionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<BookBaseResponse>> getFavouriteBooks() {
        return ResponseEntity.ok(bookService.getFavouriteBooks()
                .stream()
                .map(BookMapper::mapToBookBaseResponse)
                .toList()
        );
    }

    @Transactional
    @PostMapping("/favorites")
    public ResponseEntity<Void> addFavouriteBooks(@RequestBody @Valid AddRemoveBookFavouriteRequest request) {
        bookService.addBookToFavorites(request);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/favorites")
    public ResponseEntity<Void> removeBookFromFavorites(@RequestBody @Valid AddRemoveBookFavouriteRequest request) {
        bookService.removeBookFromFavorites(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookBaseResponse>> searchBook(
            @RequestParam(name = "q", required = false) String query
    ) {
        return ResponseEntity.ok(
                bookService.searchBooks(query)
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<BookBaseResponse>> getBooksByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(
                bookService.getBooksByCategoryId(categoryId)
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/subcategories/{subcategoryId}")
    public ResponseEntity<List<BookBaseResponse>> getBooksBySubcategoryId(@PathVariable("subcategoryId") Integer categoryId) {
        return ResponseEntity.ok(
                bookService.getBooksBySubcategoryId(categoryId)
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/recent")
    public ResponseEntity<List<BookBaseResponse>> getRecentViewBooks() {
        return ResponseEntity.ok(
                bookService.getRecentViewBooks()
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/top")
    public ResponseEntity<List<BookBaseResponse>> getTopBooks() {
        return ResponseEntity.ok(
                bookService.getTopBooks()
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/best-offer")
    public ResponseEntity<List<BookBaseResponse>> getBooksWithBestOffer() {
        return ResponseEntity.ok(
                bookService.getBookWithBestOffer()
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }

    @GetMapping("/new")
    public ResponseEntity<List<BookBaseResponse>> getNewestBooks() {
        return ResponseEntity.ok(
                bookService.getNewBooks()
                        .stream()
                        .map(BookMapper::mapToBookBaseResponse)
                        .toList()
        );
    }
}

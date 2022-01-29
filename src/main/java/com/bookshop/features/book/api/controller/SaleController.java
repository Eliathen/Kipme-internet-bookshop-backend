package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SetSaleRequest;
import com.bookshop.features.book.domain.service.port.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<Void> setSaleForCategory(@PathVariable Integer categoryId, @RequestBody SetSaleRequest request) {
        saleService.setSaleForCategory(request, categoryId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/books/{bookId}")
    public ResponseEntity<Void> setSaleForBook(@PathVariable Long bookId, @RequestBody SetSaleRequest request) {
        saleService.setSaleForBook(request, bookId);
        return ResponseEntity.noContent().build();
    }
}

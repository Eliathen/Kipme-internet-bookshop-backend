package com.bookshop.features.basket.api.controller;


import com.bookshop.features.basket.api.BasketService;
import com.bookshop.features.basket.api.response.BasketItemResponse;
import com.bookshop.features.basket.api.response.BasketResponse;
import com.bookshop.features.basket.mapper.BasketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public ResponseEntity<BasketResponse> getUserBasket() {
        return ResponseEntity.ok(BasketMapper.mapBasketEntityToBasketResponse(basketService.getUserBasket()));
    }

    @Transactional
    @PostMapping("/items/{bookId}")
    public ResponseEntity<BasketItemResponse> addBookToBasket(@PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(BasketMapper.mapBasketItemEntityToBasketResponse(basketService.addBookToBasket(bookId)));
    }

    @Transactional
    @DeleteMapping("/items/{bookId}")
    public ResponseEntity<Void> removeBookFromBasket(@PathVariable(name = "bookId") Long bookId) {
        basketService.removeBookFromBasket(bookId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/items/{bookId}/increase")
    public ResponseEntity<BasketItemResponse> increaseQuantityOfBook(@PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(BasketMapper.mapBasketItemEntityToBasketResponse(basketService.increaseQuantityOfBook(bookId)));
    }

    @Transactional
    @PatchMapping("/items/{bookId}/decrease")
    public ResponseEntity<BasketItemResponse> decreaseQuantityOfBook(@PathVariable(name = "bookId") Long bookId) {
        return ResponseEntity.ok(BasketMapper.mapBasketItemEntityToBasketResponse(basketService.decreaseQuantityOfBook(bookId)));
    }
}

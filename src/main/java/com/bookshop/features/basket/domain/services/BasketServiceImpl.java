package com.bookshop.features.basket.domain.services;

import com.bookshop.features.basket.api.BasketService;
import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.basket.data.entity.BasketItemEntity;
import com.bookshop.features.basket.domain.repository.BasketRepository;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.exception.BookNotFound;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookService bookService;

    @Override
    public BasketEntity getUserBasket() {
        var currentUser = userService.getCurrentUser();
        return Optional.ofNullable(currentUser.getBasketEntity())
                .orElseGet(() -> saveBasket(currentUser)
                );
    }

    private BasketEntity saveBasket(UserEntity user) {
        var basket = BasketEntity.builder()
                .modifiedAt(LocalDateTime.now())
                .build();
        user.setBasketEntity(basket);
        return userRepository.saveUser(user).getBasketEntity();
    }

    @Override
    public BasketItemEntity addBookToBasket(Long bookId) {
        var currentUser = userService.getCurrentUser();
        BasketEntity basketEntity = Optional.ofNullable(currentUser.getBasketEntity())
                .orElseGet(() -> saveBasket(currentUser)
                );
        BookEntity book = bookService.getBookById(bookId);
        if (basketEntity.getItems() != null && basketEntity.getItems().stream().anyMatch(item -> item.getBook().getId().equals(bookId))) {
            return increaseQuantityOfBook(bookId);
        }
        BasketItemEntity basketItemEntity = BasketItemEntity.builder()
                .book(book)
                .quantity(1)
                .build();
        basketEntity.addItem(basketItemEntity);
        return basketRepository.saveBasket(basketEntity).getItems().stream().filter(item -> item.getBook().getId().equals(bookId)).findFirst().orElseThrow(AssertionError::new);
    }

    @Override
    public void removeBookFromBasket(Long bookId) {
        var currentUser = userService.getCurrentUser();
        BasketEntity basketEntity = currentUser.getBasketEntity();
        basketEntity.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .ifPresent(basketEntity::removeItem);
        basketRepository.saveBasket(basketEntity);
    }

    @Override
    public BasketItemEntity increaseQuantityOfBook(Long bookId) {
        BasketEntity basketEntity = userService.getCurrentUser().getBasketEntity();
        BasketItemEntity basketItemEntity = basketEntity.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new BookNotFound(bookId));
        basketItemEntity.incrementQuantity();
        basketRepository.saveBasket(basketEntity);
        return basketItemEntity;
    }


    @Override
    public BasketItemEntity decreaseQuantityOfBook(Long bookId) {
        BasketEntity basketEntity = userService.getCurrentUser().getBasketEntity();
        BasketItemEntity basketItemEntity = basketEntity.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new BookNotFound(bookId));
        basketItemEntity.decrementQuantity();
        basketRepository.saveBasket(basketEntity);
        return basketItemEntity;
    }
}

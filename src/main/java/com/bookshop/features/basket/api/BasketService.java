package com.bookshop.features.basket.api;


import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.basket.data.entity.BasketItemEntity;

public interface BasketService {

    BasketEntity getUserBasket();

    BasketItemEntity addBookToBasket(Long bookId);

    void removeBookFromBasket(Long bookId);

    BasketItemEntity increaseQuantityOfBook(Long bookId);

    BasketItemEntity decreaseQuantityOfBook(Long bookId);

}

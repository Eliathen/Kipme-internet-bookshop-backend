package com.bookshop.features.order.domain.model;

import com.bookshop.features.book.domain.model.Book;
import lombok.Builder;

@Builder
public class BookPosition {

    private Long id;

    private Book orderedBook;

}

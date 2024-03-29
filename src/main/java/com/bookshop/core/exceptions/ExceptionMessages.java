package com.bookshop.core.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String USER_NOT_FOUND_EXCEPTION = "User not found";

    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "Category with id %s not found";

    public static final String LANGUAGE_NOT_FOUND = "Language with id %s not found";

    public static final String PUBLISHER_NOT_FOUND = "Publisher with id %s not found";

    public static final String AUTHOR_NOT_FOUND = "Author with id %s not found";

    public static final String BOOK_NOT_FOUND = "Book with id %s not found";

    public static final String USER_ALREADY_EXISTS = "User already exists";

    public static final String INVALID_EMAIL_ADDRESS_EXCEPTION = "Invalid email address";

    public static final String INVALID_EMAIL_ADDRESS_OR_PASSWORD = "Invalid email od password";

    public static final String COVER_NOT_FOUND = "Cover not found";

    public static final String ADDRESS_NOT_FOUND = "Address not found";

    public static final String SUBCATEGORY_WITH_ID_NOT_FOUND = "Subcategory with id = %s not found";

    public static final String INVALID_SALE_VALUE = "Invalid sale's value";

    public static final String INVALID_SALE_UNIT_CATEGORY = "Category's sale can take only percent value";

    public static final String BOOK_WITH_ID_ALREADY_EXISTS = "Book with isbn = %s already exists";

    public static final String EMPTY_COVER = "Empty cover";

    public static final String PAYMENT_NOT_FOUND = "Payment method not found";

    public static final String DELIVERY_METHOD_NOT_FOUND = "Delivery method not found";

    public static final String NOT_ENOUGH_BOOKS = "There is not enough book with id = %s";

    public static final String EMPTY_BASKET = "Empty basket";
}

package com.bookshop.features.utils;

import com.bookshop.core.security.UserRole;
import com.bookshop.features.book.data.entity.*;
import com.bookshop.features.user.data.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class BookUtils {

    public static final String BOOK_TITLE = "Kotlin in action";

    public static BookEntity getBook() {
        return BookEntity.builder()
                .id(1L)
                .title("Kotlin in action")
                .price(BigDecimal.valueOf(67L))
                .description("""
                        Kotlin in Action guides experienced Java developers from the language basics of Kotlin all the way through building applications to run on the JVM and Android devices.
                        """)
                .isbn("9781617293290")
                .publishedYear(2017)
                .quantity(60)
                .opinions(List.of(
                        opinion()
                ))
                .bookAuthors(List.of(
                        getAuthorDmitryJemerow(),
                        getAuthorSvetlanaIsakova()
                ))
                .sales(Collections.singletonList(
                        get10PercentSale())
                )
                .subcategories(List.of(getSubcategory()))
                .language(getLanguage())
                .isAvailable(true)
                .category(getCategory())
                .bookPublishers(List.of(getPublisher()))
                .isFavorite(false)
                .build();
    }

    public static OpinionEntity opinion() {
        return OpinionEntity
                .builder()
                .date(LocalDateTime.now())
                .rating(5.0)
                .user(getUser())
                .build();
    }

    public static UserEntity getUser() {
        return UserEntity.builder()
                .name("John")
                .email("john@wick.com")
                .enabled(true)
                .password("client".toCharArray())
                .role(UserRole.CLIENT)
                .surname("Wick")
                .build();
    }

    public static SaleEntity get10PercentSale() {
        return SaleEntity.builder().value(BigDecimal.valueOf(10)).saleUnit(SaleUnit.PERCENT).isActive(true).build();
    }

    public static AuthorEntity getAuthorSvetlanaIsakova() {
        return AuthorEntity.builder()
                .id(2)
                .name("Svetlana")
                .surname("Isakova")
                .build();
    }

    public static AuthorEntity getAuthorDmitryJemerow() {
        return AuthorEntity.builder()
                .id(1)
                .name("Dmitry")
                .surname("Jemerov")
                .build();
    }

    public static PublisherEntity getPublisher() {
        return PublisherEntity.builder()
                .id(1)
                .publisherName("Helion SA")
                .publisherCity("Gliwice")
                .build();
    }

    public static LanguageEntity getLanguage() {

        return LanguageEntity.builder()
                .id(1)
                .name("English")
                .build();
    }

    public static CategoryEntity getCategory() {
        return CategoryEntity.builder().id(1)
                .name("Information Technology")
                .subcategories(List.of(
                        getSubcategory()
                ))
                .build();
    }

    public static SubcategoryEntity getSubcategory() {
        return SubcategoryEntity.builder()
                .id(1)
                .name("Programming")
                .build();
    }
}

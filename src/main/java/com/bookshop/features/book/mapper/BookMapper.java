package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.api.response.BookBaseResponse;
import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.data.entity.BookEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookMapper {

    public static BookResponse mapToBookResponse(BookEntity book) {
        return BookResponse.builder()
                .id(book.getId())
                .rating(book.getAvgRating())
                .bookAuthors(book.getBookAuthors().stream()
                        .map(AuthorMapper::mapToAuthorResponse)
                        .toList()
                )
                .bookPublishers(book.getBookPublishers().stream()
                        .map(PublisherMapper::mapToPublisherResponse)
                        .toList()
                )
                .bookOpinions((book.getOpinions() != null) ?
                        book.getOpinions().stream()
                                .map(OpinionMapper::mapOpinionEntityToOpinionResponse)
                                .toList() :
                        new ArrayList<>()
                )
                .category(CategoryMapper.mapToCategoryWithoutSubcategoriesResponse(book.getCategory()))
                .description(book.getDescription())
                .salePrice(book.getCurrentPrice())
                .isbn(book.getIsbn())
                .isFavorite(book.isFavorite())
                .language(LanguageMapper.mapToLanguageResponse(book.getLanguage()))
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .numberOfRatings((book.getOpinions() != null) ? book.getOpinions().size() : 0)
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryResponse).toList())
                .build();
    }

    public static BookEntity mapToBookEntity(SaveBookRequest request) {
        return BookEntity.builder()
                .title(request.title())
                .isbn(request.isbn())
                .publishedYear(request.publishedYear())
                .description(request.description())
                .quantity(request.quantity())
                .price(request.price())
                .build();
    }

    public static BookBaseResponse mapToBookBaseResponse(BookEntity bookEntity) {
        return BookBaseResponse.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .price(bookEntity.getPrice())
                .salePrice(bookEntity.getCurrentPrice())
                .bookAuthors(bookEntity.getBookAuthors().stream().map(AuthorMapper::mapToAuthorResponse).collect(Collectors.toList()))
                .isFavorite(bookEntity.isFavorite())
                .rating(bookEntity.getAvgRating())
                .numberOfRatings((bookEntity.getOpinions() != null) ? bookEntity.getOpinions().size() : 0)
                .build();
    }
}

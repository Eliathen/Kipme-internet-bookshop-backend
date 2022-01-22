package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.data.entity.BookEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookResponse mapToBookResponse(BookEntity book) {
        return BookResponse.builder()
                .id(book.getId())
                .rating(book.getAvgRating())
                .bookAuthors(book.getBookAuthors().stream().map(AuthorMapper::mapToAuthorResponse).collect(Collectors.toList()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapToPublisherResponse).collect(Collectors.toList()))
                .bookOpinions((book.getOpinions() != null) ?
                        book.getOpinions().stream().map(OpinionMapper::mapOpinionEntityToOpinionResponse).collect(Collectors.toList()) :
                        new ArrayList<>())
                .category(CategoryMapper.mapToCategoryWithoutSubcategoriesResponse(book.getCategory()))
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .isFavorite(book.isFavorite())
                .language(LanguageMapper.mapToLanguageResponse(book.getLanguage()))
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }

    public static BookEntity mapToBookEntity(SaveBookRequest request){
        return BookEntity.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .publishedYear(request.getPublishedYear())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }
}

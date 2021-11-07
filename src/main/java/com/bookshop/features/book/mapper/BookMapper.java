package com.bookshop.features.book.mapper;

import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.opinion.domain.model.Opinion;
import com.bookshop.features.opinion.mapper.OpinionMapper;

import java.util.function.Function;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookEntity mapToBookEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .cover(CoverMapper.mapToCoverEntity(book.getCover()))
                .bookAuthors(book.getBookAuthors().stream().map(AuthorMapper::mapToAuthorEntity).collect(Collectors.toSet()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapToPublisherEntity).collect(Collectors.toSet()))
                .category(CategoryMapper.mapToCategoryEntity(book.getCategory()))
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .language(LanguageMapper.mapToLanguageEntity(book.getLanguage()))
                .opinions(book.getBookOpinions().stream().map(OpinionMapper::mapToOpinionEntity).collect(Collectors.toSet()))
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryEntity).collect(Collectors.toSet()))
                .build();
    }

}

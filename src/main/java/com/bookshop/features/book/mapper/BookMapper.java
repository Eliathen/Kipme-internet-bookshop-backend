package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.magazine.data.entity.MagazineStateEntity;
import com.bookshop.features.opinion.domain.model.Opinion;
import com.bookshop.features.opinion.mapper.OpinionMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookEntity mapToBookEntity(Book book) {
        LinkedList<AuthorEntity> authors = book.getBookAuthors().stream().map(AuthorMapper::mapToAuthorEntity).collect(Collectors.toCollection(LinkedList::new));
        BookEntity bookEntity = BookEntity.builder()
                .id(book.getId())
                .cover(CoverMapper.mapToCoverEntity(book.getCover()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapToPublisherEntity).collect(Collectors.toList()))
                .category(CategoryMapper.mapToCategoryEntity(book.getCategory()))
                .description(book.getDescription())
                .bookAuthors(new LinkedList<>())
                .isbn(book.getIsbn())
                .opinions(new LinkedList<>())
                .bookOrders(new LinkedList<>())
                .orderPositions(new LinkedList<>())
                .language(LanguageMapper.mapToLanguageEntity(book.getLanguage()))
                .opinions((book.getBookOpinions() != null) ? book.getBookOpinions().stream()
                        .map(OpinionMapper::mapToOpinionEntity).collect(Collectors.toList()) : new LinkedList<>()
                )
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryEntity).collect(Collectors.toList()))
                .build();

        bookEntity.setMagazineState(MagazineStateEntity.builder()
                .book(bookEntity)
                .amount(book.getAmount())
                .build());
        authors.forEach(authorEntity -> authorEntity.getAuthorsBooks().add(bookEntity));
        bookEntity.getBookAuthors().addAll(authors);
        return bookEntity;
    }

    public static Book mapToBook(BookEntity bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .cover(CoverMapper.mapToCover(bookEntity.getCover()))
                .bookAuthors(bookEntity.getBookAuthors().stream().map(AuthorMapper::mapToAuthor).collect(Collectors.toList()))
                .bookPublishers(bookEntity.getBookPublishers().stream().map(PublisherMapper::mapToPublisher).collect(Collectors.toList()))
                .category(CategoryMapper.mapToCategory(bookEntity.getCategory()))
                .description(bookEntity.getDescription())
                .isbn(bookEntity.getIsbn())
                .language(LanguageMapper.mapToLanguage(bookEntity.getLanguage()))
                .price(bookEntity.getPrice())
                .publishedYear(bookEntity.getPublishedYear())
                .quantity(bookEntity.getQuantity())
                .title(bookEntity.getTitle())
                .subcategories(bookEntity.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategory).collect(Collectors.toList()))
                .build();
    }

    public static BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .cover(CoverMapper.mapToCoverResponse(book.getCover()))
                .bookAuthors(book.getBookAuthors().stream().map(AuthorMapper::mapToAuthorResponse).collect(Collectors.toList()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapToPublisherResponse).collect(Collectors.toList()))
                .category(CategoryMapper.mapToCategoryResponse(book.getCategory()))
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .language(LanguageMapper.mapToLanguageResponse(book.getLanguage()))
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }
}

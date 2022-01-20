package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.response.BookResponse;
import com.bookshop.features.book.data.entity.AuthorEntity;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.magazine.data.entity.MagazineStateEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookEntity mapBookToBookEntity(Book book) {
        LinkedList<AuthorEntity> authors = book.getBookAuthors().stream().map(AuthorMapper::mapAuthorToAuthorEntity).collect(Collectors.toCollection(LinkedList::new));
        BookEntity bookEntity = BookEntity.builder()
                .id(book.getId())
                .cover(CoverMapper.mapCoverToCoverEntity(book.getCover()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapPublisherToPublisherEntity).collect(Collectors.toList()))
                .opinions((book.getBookOpinions() != null) ?
                        book.getBookOpinions().stream().map(OpinionMapper::mapToOpinionEntity).collect(Collectors.toList()) :
                        new ArrayList<>())
                .category(CategoryMapper.mapCategoryToCategoryEntity(book.getCategory()))
                .description(book.getDescription())
                .bookAuthors(new LinkedList<>())
                .isbn(book.getIsbn())
                .opinions(new LinkedList<>())
                .bookOrders(new LinkedList<>())
                .orderPositions(new LinkedList<>())
                .language(LanguageMapper.mapLanguageToLanguageEntity(book.getLanguage()))
                .opinions((book.getBookOpinions() != null) ? book.getBookOpinions().stream()
                        .map(OpinionMapper::mapToOpinionEntity).collect(Collectors.toList()) : new LinkedList<>()
                )
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryToSubcategoryEntity).collect(Collectors.toList()))
                .build();

        bookEntity.setMagazineState(MagazineStateEntity.builder()
                .book(bookEntity)
                .amount(bookEntity.getQuantity())
                .build());
        authors.forEach(authorEntity -> authorEntity.getAuthorsBooks().add(bookEntity));
        bookEntity.getBookAuthors().addAll(authors);
        return bookEntity;
    }

    public static Book mapBookEntityToBook(BookEntity bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .cover(CoverMapper.mapCoverEntityToCover(bookEntity.getCover()))
                .bookAuthors(bookEntity.getBookAuthors().stream().map(AuthorMapper::mapAuthorEntityToAuthor).collect(Collectors.toList()))
                .bookPublishers(bookEntity.getBookPublishers().stream().map(PublisherMapper::mapPublisherEntityToPublisher).collect(Collectors.toList()))
                .bookOpinions((bookEntity.getOpinions() != null) ?
                        bookEntity.getOpinions().stream().map(OpinionMapper::mapToOpinion).collect(Collectors.toList()) :
                        new ArrayList<>())
                .category(CategoryMapper.mapCategoryEntityToCategory(bookEntity.getCategory()))
                .description(bookEntity.getDescription())
                .isbn(bookEntity.getIsbn())
                .language(LanguageMapper.mapLanguageEntityToLanguage(bookEntity.getLanguage()))
                .price(bookEntity.getPrice())
                .publishedYear(bookEntity.getPublishedYear())
                .quantity(bookEntity.getQuantity())
                .title(bookEntity.getTitle())
                .subcategories(bookEntity.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryEntityToSubcategory).collect(Collectors.toList()))
                .build();
    }

    public static BookResponse mapBookToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .bookAuthors(book.getBookAuthors().stream().map(AuthorMapper::mapAuthorToAuthorResponse).collect(Collectors.toList()))
                .bookPublishers(book.getBookPublishers().stream().map(PublisherMapper::mapPublisherToPublisherResponse).collect(Collectors.toList()))
                .bookOpinions((book.getBookOpinions() != null) ?
                        book.getBookOpinions().stream().map(OpinionMapper::mapToOpinionResponse).collect(Collectors.toList()) :
                        new ArrayList<>())
                .category(CategoryMapper.mapCategoryToCategoryResponse(book.getCategory()))
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .language(LanguageMapper.mapLanguageToLanguageResponse(book.getLanguage()))
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .rating(book.getAvgRating())
                .subcategories(book.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }
}

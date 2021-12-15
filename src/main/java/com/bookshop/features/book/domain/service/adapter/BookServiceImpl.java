package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.domain.model.*;
import com.bookshop.features.book.domain.repository.AuthorRepository;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.domain.service.port.CategoryService;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.mapper.AuthorMapper;
import com.bookshop.features.opinion.domain.model.Opinion;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageService languageService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;

    @Override
    public Book saveBook(SaveBookRequest request, MultipartFile cover) throws IOException {
        Cover newCover = getCoverFromMultipartFile(cover);
        Language language = languageService.getLanguage(request.getLanguageId());
        List<Publisher> publisherList = publisherService.getPublishers(new LinkedList<>(request.getBookPublishersIds()));
        Category category = categoryService.getCategory(request.getCategoryId());
        List<Subcategory> subcategories = category.getSubcategories().stream().filter(sub -> request.getSubcategoriesIds().contains(sub.getId())).collect(Collectors.toList());
        List<Author> authors = request.getBookAuthors().stream().map(
                author -> authorRepository.getAuthorByNameAndSurname(AuthorMapper.mapToAuthor(author))
        ).collect(Collectors.toList());
        Book book = Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .publishedYear(request.getPublishedYear())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .bookAuthors(authors)
                .bookPublishers(publisherList)
                .language(language)
                .cover(newCover)
                .category(category)
                .subcategories(subcategories)
                .amount(request.getAmount())
                .build();
        return bookRepository.saveBook(book);
    }


    private Cover getCoverFromMultipartFile(MultipartFile cover) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(cover.getOriginalFilename()));
        return Cover.builder()
                .name(fileName)
                .type(cover.getContentType())
                .data(cover.getBytes())
                .build();
    }
}

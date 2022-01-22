package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.data.entity.*;
import com.bookshop.features.book.domain.repository.AuthorRepository;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.domain.service.port.CategoryService;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.exception.BookNotFound;
import com.bookshop.features.book.exception.CoverNotFound;
import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.book.mapper.OpinionMapper;
import com.bookshop.features.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageService languageService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public BookEntity saveBook(SaveBookRequest request, MultipartFile cover) throws IOException {
        CoverEntity newCover = getCoverFromMultipartFile(cover);
        LanguageEntity language = languageService.getLanguage(request.getLanguageId());
        List<PublisherEntity> publisherList = publisherService.getPublishers(new LinkedList<>(request.getBookPublishersIds()));
        CategoryEntity category = categoryService.getCategory(request.getCategoryId());
        List<SubcategoryEntity> subcategories = category.getSubcategories().stream().filter(sub -> request.getSubcategoriesIds().contains(sub.getId())).collect(Collectors.toList());
        List<AuthorEntity> authors = request.getBookAuthors().stream().map(
                author -> authorRepository.getAuthorByNameAndSurnameOrSave(author.getName(), author.getSurname())
        ).collect(Collectors.toList());
        BookEntity book = BookMapper.mapToBookEntity(request);
        book.setCover(newCover);
        book.setLanguage(language);
        book.setBookPublishers(publisherList);
        book.setCategory(category);
        book.setSubcategories(subcategories);
        book.setBookAuthors(authors);
        authors.forEach(author -> {
            if (author.getAuthorsBooks() != null) {
                author.getAuthorsBooks().add(book);
            } else {
                author.setAuthorsBooks(List.of(book));
            }
        });
        return bookRepository.saveBook(book);
    }

    @Override
    public BookEntity getBookById(Long id) {
        return bookRepository.getBookById(id).orElseThrow(() -> new BookNotFound(id));
    }

    @Override
    public CoverEntity getCoverByBookId(Long bookId) {
        BookEntity book = bookRepository.getBookById(bookId).orElseThrow(CoverNotFound::new);
        return book.getCover();
    }

    @Override
    public void saveOpinion(Long bookId, AddOpinionRequest request) {
        var book = getBookById(bookId);
        var user = userService.getCurrentUser();
        var opinion = OpinionMapper.mapToOpinionEntity(request);
        opinion.setBook(book);
        opinion.setUser(user);
        bookRepository.saveOpinion(opinion);
    }

    @Override
    public void removeOpinion(Long bookId, Integer opinionId) {
        var user = userService.getCurrentUser();
        var book = getBookById(bookId);
        var opinion = book.getOpinions().stream()
                .filter(opinionEntity -> Objects.equals(opinionEntity.getId(), opinionId) && Objects.equals(opinionEntity.getUser().getId(), user.getId()))
                .findFirst();
        opinion.ifPresent(opinionEntity -> book.getOpinions().remove(opinionEntity));
    }

    private CoverEntity getCoverFromMultipartFile(MultipartFile cover) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(cover.getOriginalFilename()));
        return CoverEntity.builder()
                .name(fileName)
                .type(cover.getContentType())
                .data(cover.getBytes())
                .build();
    }
}

package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.core.config.CacheConfig;
import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.request.AddRemoveBookFavouriteRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.data.entity.*;
import com.bookshop.features.book.domain.repository.AuthorRepository;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.bookshop.features.book.domain.repository.SaleRepository;
import com.bookshop.features.book.domain.service.port.BookService;
import com.bookshop.features.book.domain.service.port.CategoryService;
import com.bookshop.features.book.domain.service.port.LanguageService;
import com.bookshop.features.book.domain.service.port.PublisherService;
import com.bookshop.features.book.exception.BookNotFound;
import com.bookshop.features.book.exception.BookWithIsbnAlreadyExists;
import com.bookshop.features.book.exception.CoverNotFound;
import com.bookshop.features.book.exception.EmptyCoverException;
import com.bookshop.features.book.mapper.BookMapper;
import com.bookshop.features.book.mapper.OpinionMapper;
import com.bookshop.features.user.api.UserService;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    private final LanguageService languageService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final UserService userService;

    private final CacheConfig cacheConfig;

    @Override
    public BookEntity saveBook(SaveBookRequest request, MultipartFile cover) throws IOException {
        bookRepository.getBookByIsbn(request.isbn()).ifPresent((isbn) -> {
            throw new BookWithIsbnAlreadyExists(request.isbn());
        });
        BookEntity book = BookMapper.mapToBookEntity(request);
        CoverEntity newCover = getCoverFromMultipartFile(cover);
        LanguageEntity language = languageService.getLanguage(request.languageId());
        List<PublisherEntity> publisherList = publisherService.getPublishers(new LinkedList<>(request.bookPublishersIds()));
        CategoryEntity category = categoryService.getCategory(request.categoryId());
        List<SubcategoryEntity> subcategories = category.getSubcategories().stream().filter(sub -> request.subcategoriesIds().contains(sub.getId())).collect(Collectors.toList());
        List<AuthorEntity> authors = request.bookAuthors().stream().map(
                author -> authorRepository.getAuthorByNameAndSurnameOrSave(author.name(), author.surname())
        ).collect(Collectors.toList());
        book.setAddedAt(LocalDateTime.now());
        book.setIsAvailable(true);
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
        UserEntity currentUser = userService.getCurrentUser();
        Optional<BookEntity> book = bookRepository.getAvailableBookById(id);
        if (book.isPresent() && cacheConfig.isAvailable()) {
            bookRepository.saveBookIdForUser(currentUser.getId(), book.get().getId());
        }
        return markBookIfFavorite(book.orElseThrow(() -> new BookNotFound(id)), currentUser.getId());
    }

    @Override
    public CoverEntity getCoverByBookId(Long bookId) {
        BookEntity book = bookRepository.getAvailableBookById(bookId).orElseThrow(CoverNotFound::new);
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

    @Override
    public List<BookEntity> getFavouriteBooks() {
        return markAllBooksIfFavorite(userService.getCurrentUser().getFavouriteBooks());
    }

    @Override
    public void addBookToFavorites(AddRemoveBookFavouriteRequest request) {
        var book = getBookById(request.bookId());
        var user = userService.getCurrentUser();
        addBookToFavorites(user, book);
    }

    @Override
    public void removeBookFromFavorites(AddRemoveBookFavouriteRequest request) {
        var book = getBookById(request.bookId());
        var user = userService.getCurrentUser();
        user.getFavouriteBooks().removeIf(bookEntity -> bookEntity.getId().equals(book.getId()));
        userRepository.saveUser(user);
    }

    @Override
    public List<BookEntity> searchBooks(String query) {
        Set<BookEntity> result = new HashSet<>();
        Optional.of(query).ifPresent(que -> result.addAll(bookRepository.findByTitleOrAuthorNameOrAuthorSurname(que)));
        return markAllBooksIfFavorite(new ArrayList<>(result));
    }

    @Override
    public List<BookEntity> getBooksByCategoryId(Integer categoryId) {
        return markAllBooksIfFavorite(categoryService.getCategory(categoryId).getAvailableBooks());
    }

    @Override
    public List<BookEntity> getBooksBySubcategoryId(Integer categoryId) {
        return markAllBooksIfFavorite(categoryService.getSubcategoryById(categoryId).getAvailableBooks());
    }

    @Override
    public List<BookEntity> getRecentViewBooks() {
        return bookRepository.getLastViewsBooksByUser(userService.getCurrentUser().getId());
    }

    @Override
    public List<BookEntity> getTopBooks() {
        return bookRepository.getTopBooks();
    }

    @Override
    public List<BookEntity> getBookWithBestOffer() {
        var sales = saleRepository.getActiveSales();
        return sales.stream().flatMap(saleEntity -> Stream.of(saleEntity.getAvailableBooks()))
                .flatMap(Collection::stream)
                .distinct()
                .sorted(Comparator.comparing(BookEntity::getCurrentPrice))
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookEntity> getNewBooks() {
        return bookRepository.getNewBooks();
    }

    private void addBookToFavorites(UserEntity user, BookEntity book) {
        if (getFavouriteBooks().stream().anyMatch(bookEntity -> bookEntity.getId().equals(book.getId()))) {
            return;
        }
        if (getFavouriteBooks() == null) {
            user.setFavouriteBooks(List.of(book));
        }
        user.getFavouriteBooks().add(book);
        userRepository.saveUser(user);
    }

    private CoverEntity getCoverFromMultipartFile(MultipartFile cover) throws IOException {
        if (cover == null || cover.isEmpty()) throw new EmptyCoverException(ExceptionMessages.EMPTY_COVER);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(cover.getOriginalFilename()));
        return CoverEntity.builder()
                .name(fileName)
                .type(cover.getContentType())
                .data(cover.getBytes())
                .build();
    }

    private List<BookEntity> markAllBooksIfFavorite(List<BookEntity> books) {
        var userId = userService.getCurrentUser().getId();
        books.forEach(book -> markBookIfFavorite(book, userId));
        return books;
    }

    private BookEntity markBookIfFavorite(BookEntity book, Long userId) {
        book.getUsers().stream().filter(userEntity -> userEntity.getId().equals(userId)).findFirst().ifPresent(
                (userEntity) -> book.setFavorite(true)
        );
        return book;
    }
}

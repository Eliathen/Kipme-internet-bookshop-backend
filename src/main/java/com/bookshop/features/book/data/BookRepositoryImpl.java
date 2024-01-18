package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.OpinionEntity;
import com.bookshop.features.book.data.jpa.BookJpaRepository;
import com.bookshop.features.book.data.jpa.OpinionJpaRepository;
import com.bookshop.features.book.domain.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository jpa;
    private final OpinionJpaRepository opinionJpaRepository;
    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    @Override
    public BookEntity saveBook(BookEntity book) {
        return jpa.saveAndFlush(book);
    }

    @Override
    public Optional<BookEntity> getAvailableBookById(Long id) {
        return jpa.getBookEntityByIdAndIsAvailable(id, true);
    }

    @Override
    public void saveOpinion(OpinionEntity opinion) {
        opinionJpaRepository.saveAndFlush(opinion);
    }

    @Override
    public List<BookEntity> findByTitleOrAuthorNameOrAuthorSurname(String query) {
        Set<BookEntity> result = new HashSet<>(jpa.getBookEntityByTitleQuery(query));
        for (String s : query.split(" ")) {
            result.addAll(jpa.getBookEntityByTitleQuery(s));
        }
        return result.stream().filter(BookEntity::isAvailable).distinct().toList();
    }

    @Override
    public Optional<BookEntity> getBookByIsbn(String isbn) {
        return jpa.getBookEntityByIsbn(isbn);
    }

    @Override
    @SneakyThrows
    public void saveBookIdForUser(Long userId, Long bookId) {
        var key = "recent/" + userId + "#ids";
        String result = jedis.get(key);
        var ids = List.of(String.valueOf(bookId));
        if (result != null) {
            ids = new ArrayList<>(Arrays.asList(objectMapper.readValue(result, String[].class)));
            if (ids.size() > 10) {
                ids.remove(0);
            }
            if (!ids.contains(String.valueOf(bookId))) {
                ids.add(String.valueOf(bookId));
            }
        }
        jedis.set(key, objectMapper.writeValueAsString(ids));
    }

    @Override
    @SneakyThrows
    public List<BookEntity> getLastViewsBooksByUser(Long userId) {
        var key = "recent/" + userId + "#ids";
        String result;
        try {
            result = jedis.get(key);
        } catch (JedisConnectionException e) {
            return Collections.emptyList();
        }
        if (result != null) {
            System.out.println(result);
            List<String> ids = new ArrayList<>(Arrays.asList(objectMapper.readValue(result, String[].class)));
            return jpa.findAllById(ids.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList()))
                    .stream().filter(BookEntity::getIsAvailable)
                    .collect(Collectors.toList());
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<BookEntity> getTopBooks() {
        return jpa.getTopBooks().stream().filter(BookEntity::getIsAvailable).collect(Collectors.toList());
                .toList();
    }

    @Override
    public List<BookEntity> getNewBooks() {
        return jpa.findBookEntityByAddedAtAfter(LocalDateTime.now().minusDays(7));
    }
}

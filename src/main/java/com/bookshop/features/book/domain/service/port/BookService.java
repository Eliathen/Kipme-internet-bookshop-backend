package com.bookshop.features.book.domain.service.port;


import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.book.domain.model.Cover;
import com.bookshop.features.book.domain.model.Opinion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {

    Book saveBook(SaveBookRequest request, MultipartFile cover) throws IOException;

    Book getBookById(Long id);

    Cover getCoverByBookId(Long bookId);

    void saveOpinion(Long id, Opinion opinion);

    void removeOpinion(Long bookId, Integer opinionId);
}

package com.bookshop.features.book.domain.service.port;


import com.bookshop.features.book.api.request.AddOpinionRequest;
import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.CoverEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {

    BookEntity saveBook(SaveBookRequest request, MultipartFile cover) throws IOException;

    BookEntity getBookById(Long id);

    CoverEntity getCoverByBookId(Long bookId);

    void saveOpinion(Long bookId, AddOpinionRequest opinion);

    void removeOpinion(Long bookId, Integer opinionId);
}

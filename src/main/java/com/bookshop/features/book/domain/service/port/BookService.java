package com.bookshop.features.book.domain.service.port;


import com.bookshop.features.book.api.request.SaveBookRequest;
import com.bookshop.features.book.domain.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {

    Book saveBook(SaveBookRequest request, MultipartFile cover) throws IOException;

}

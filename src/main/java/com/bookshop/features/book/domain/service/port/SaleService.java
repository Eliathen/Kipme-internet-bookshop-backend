package com.bookshop.features.book.domain.service.port;


import com.bookshop.features.book.api.request.SetSaleRequest;

public interface SaleService {

    void setSaleForBook(SetSaleRequest request, Long bookId);

    void setSaleForCategory(SetSaleRequest request, Integer categoryId);
}

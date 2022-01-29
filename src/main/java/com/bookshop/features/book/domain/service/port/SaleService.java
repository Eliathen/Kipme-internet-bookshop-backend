package com.bookshop.features.book.domain.service.port;


import com.bookshop.features.book.api.request.SetSaleRequest;
import com.bookshop.features.book.data.entity.SaleEntity;

import java.util.List;

public interface SaleService {

    void setSaleForBook(SetSaleRequest request, Long bookId);

    void setSaleForCategory(SetSaleRequest request, Integer categoryId);

    List<SaleEntity> getAvailableSales();
}

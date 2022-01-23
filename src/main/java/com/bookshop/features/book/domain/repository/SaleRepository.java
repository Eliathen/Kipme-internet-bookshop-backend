package com.bookshop.features.book.domain.repository;

import com.bookshop.features.book.data.entity.SaleEntity;

public interface SaleRepository {

    SaleEntity saveSale(SaleEntity saleEntity);

}

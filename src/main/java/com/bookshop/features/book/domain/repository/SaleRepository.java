package com.bookshop.features.book.domain.repository;

import com.bookshop.features.book.data.entity.SaleEntity;

import java.util.List;

public interface SaleRepository {

    void saveSale(SaleEntity saleEntity);

    List<SaleEntity> getAllSales();

    List<SaleEntity> getActiveSales();
}

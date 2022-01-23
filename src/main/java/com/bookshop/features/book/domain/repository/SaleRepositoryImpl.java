package com.bookshop.features.book.domain.repository;

import com.bookshop.features.book.data.entity.SaleEntity;
import com.bookshop.features.book.data.jpa.SaleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SaleRepositoryImpl implements SaleRepository {

    private final SaleJpaRepository saleJpaRepository;

    @Override
    public void saveSale(SaleEntity saleEntity) {
        saleJpaRepository.saveAndFlush(saleEntity);
    }

    @Override
    public List<SaleEntity> getAllSales() {
        return saleJpaRepository.findAll();
    }
}

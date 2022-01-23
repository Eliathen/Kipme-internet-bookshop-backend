package com.bookshop.features.book.domain.repository;

import com.bookshop.features.book.data.entity.SaleEntity;
import com.bookshop.features.book.data.jpa.SaleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SaleRepositoryImpl implements SaleRepository {

    private final SaleJpaRepository saleJpaRepository;

    @Override
    public SaleEntity saveSale(SaleEntity saleEntity) {
        return saleJpaRepository.saveAndFlush(saleEntity);
    }
}

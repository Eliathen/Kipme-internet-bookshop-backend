package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.SaleEntity;
import com.bookshop.features.book.data.jpa.SaleJpaRepository;
import com.bookshop.features.book.domain.repository.SaleRepository;
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

    @Override
    public List<SaleEntity> getActiveSales() {
        return saleJpaRepository.findAllByIsActiveIsTrue();
    }
}

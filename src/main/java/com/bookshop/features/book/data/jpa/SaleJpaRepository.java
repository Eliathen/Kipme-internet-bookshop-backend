package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleJpaRepository extends JpaRepository<SaleEntity, Long> {
}

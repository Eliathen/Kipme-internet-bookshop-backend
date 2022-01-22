package com.bookshop.features.book.data.jpa;

import com.bookshop.features.book.data.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryJpaRepository extends JpaRepository<SubcategoryEntity, Integer> {
}

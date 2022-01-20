package com.bookshop.features.book.data.jpa;


import com.bookshop.features.book.data.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageJpaRepository extends JpaRepository<LanguageEntity, Integer> {
}

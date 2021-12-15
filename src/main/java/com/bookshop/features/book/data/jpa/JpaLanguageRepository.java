package com.bookshop.features.book.data.jpa;


import com.bookshop.features.book.data.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLanguageRepository extends JpaRepository<LanguageEntity, Integer> {
}

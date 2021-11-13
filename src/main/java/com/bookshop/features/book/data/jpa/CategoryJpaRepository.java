package com.bookshop.features.book.data.jpa;


import com.bookshop.features.book.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {

}

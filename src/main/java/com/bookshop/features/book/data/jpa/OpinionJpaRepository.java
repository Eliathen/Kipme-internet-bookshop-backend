package com.bookshop.features.book.data.jpa;

import com.bookshop.features.opinion.data.entity.OpinionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionJpaRepository extends JpaRepository<OpinionEntity, Integer> {
}

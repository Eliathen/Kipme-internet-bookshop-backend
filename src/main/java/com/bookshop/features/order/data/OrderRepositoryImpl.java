package com.bookshop.features.order.data;

import com.bookshop.features.order.data.jpa.OrderJpaRepository;
import com.bookshop.features.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

}

package com.bookshop.features.user.data.jpa;

import com.bookshop.features.user.data.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
}

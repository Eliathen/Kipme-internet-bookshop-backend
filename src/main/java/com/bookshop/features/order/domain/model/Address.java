package com.bookshop.features.order.domain.model;

import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.domain.model.Order;
import com.bookshop.features.user.data.entity.UserEntity;
import com.bookshop.features.user.domain.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Address {

    private Long id;

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

}

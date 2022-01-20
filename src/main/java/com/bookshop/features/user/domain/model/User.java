package com.bookshop.features.user.domain.model;

import com.bookshop.features.order.domain.model.Address;
import com.bookshop.features.order.domain.model.Order;
import com.bookshop.features.user.data.entity.UserRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private char[] password;

    private boolean enabled;

    private Set<Order> orders = new HashSet<>();

    private UserRole role;

    private Set<Address> addresses = new HashSet<>();

}

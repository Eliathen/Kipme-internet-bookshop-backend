package com.bookshop.features.user.entity;

import com.bookshop.features.address.entity.AddressEntity;
import com.bookshop.features.opinion.entity.OpinionEntity;
import com.bookshop.features.order.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "User")
public class UserEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private Integer phoneNumber;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<OrderEntity> orders = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<OpinionEntity> opinions = new HashSet<>();

    @Getter
    @Setter
    @Column(nullable = false)
    private UserRole role;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "usersAddresses")
    private Set<AddressEntity> addressesWithUsers = new HashSet<>();
}

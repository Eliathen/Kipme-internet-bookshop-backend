package com.bookshop.features.user.data.entity;

import com.bookshop.features.order.data.entity.AddressEntity;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "User")
public class UserEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    private char[] password;

    @Getter
    @Setter
    private Boolean enabled;

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
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "usersAddresses")
    private Set<AddressEntity> addresses = new HashSet<>();
}

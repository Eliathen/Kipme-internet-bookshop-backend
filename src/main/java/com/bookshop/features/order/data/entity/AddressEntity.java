package com.bookshop.features.order.data.entity;

import com.bookshop.features.user.data.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity(name = "Address")
public class AddressEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String voivodeship;

    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String buildingNumber;

    @Getter
    @Setter
    private String flatNumber;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "address_id")
    private Set<OrderEntity> ordersWithAdresses = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
         name = "users_addresses",
         joinColumns = @JoinColumn(name = "address_id"),
         inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> usersAddresses = new HashSet<>();


}

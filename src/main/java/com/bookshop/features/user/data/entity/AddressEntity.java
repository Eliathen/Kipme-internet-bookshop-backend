package com.bookshop.features.user.data.entity;

import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity(name = "Address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voivodeship;

    private String postalCode;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;

    @OneToMany
    @JoinColumn(name = "address_id")
    private List<OrderEntity> ordersWithAdresses;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> usersAddresses;

    public void addUser(UserEntity userEntity) {
        if (usersAddresses == null) {
            usersAddresses = List.of(userEntity);
        } else {
            usersAddresses.add(userEntity);
        }
    }
}

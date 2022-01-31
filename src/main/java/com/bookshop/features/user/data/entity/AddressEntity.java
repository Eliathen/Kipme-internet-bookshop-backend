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
@Entity(name = "ADDRESS")
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
    @JoinColumn(name = "ADDRESS_ID")
    private List<OrderEntity> ordersWithAdresses;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "USERS_ADDRESSES",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
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

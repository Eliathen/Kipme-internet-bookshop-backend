package com.bookshop.features.address.entity;

import com.bookshop.features.order.entity.OrderEntity;
import com.bookshop.features.user.entity.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
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

    public AddressEntity() {
    }

    public AddressEntity(String voivodeship, String postalCode, String city, String street, String buildingNumber, String flatNumber) {
        this.voivodeship = voivodeship;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", voivodeship='" + voivodeship + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                '}';
    }

}

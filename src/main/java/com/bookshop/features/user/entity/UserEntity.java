package com.bookshop.features.user.entity;

import com.bookshop.features.address.entity.AddressEntity;
import com.bookshop.features.opinion.entity.OpinionEntity;
import com.bookshop.features.order.entity.OrderEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<OrderEntity> orders = new HashSet<>();

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<OpinionEntity> opinions = new HashSet<>();

    //private Role roleId ENUM;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "usersAddresses")
    private Set<AddressEntity> addressesWithUsers = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String name, String surname, Integer phoneNumber, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

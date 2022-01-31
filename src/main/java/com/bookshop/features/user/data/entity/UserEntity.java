package com.bookshop.features.user.data.entity;

import com.bookshop.core.security.UserRole;
import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.OpinionEntity;
import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "USER")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private char[] password;

    private Boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<OpinionEntity> opinions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "FAVOURITE_BOOKS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<BookEntity> favouriteBooks;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(mappedBy = "usersAddresses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private BasketEntity basketEntity;

    public void addAddress(AddressEntity address) {
        if (getAddresses() == null) {
            addresses = List.of(address);
        } else {
            addresses.add(address);
        }
    }


}

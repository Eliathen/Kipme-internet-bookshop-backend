package com.bookshop.core.security;

import com.bookshop.features.user.domain.model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean isEnabled;

    public static UserDetailsImpl of(User user) {
        return UserDetailsImpl.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(String.valueOf(user.getPassword()))
                .isEnabled(user.isEnabled())
                .userRole(UserRole.valueOf(user.getRole().name()))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRole.toString()));
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

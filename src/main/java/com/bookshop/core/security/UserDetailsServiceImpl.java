package com.bookshop.core.security;

import com.bookshop.features.user.domain.UserRepository;
import com.bookshop.features.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.of(userRepository.getUserByEmail(email));
    }

    public UserDetails loadUserById(Long id) {
        return UserDetailsImpl.of(userRepository.getUserById(id));
    }

    public UserDetailsImpl currentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }
        return (UserDetailsImpl) authentication.getPrincipal();
    }


    public boolean isCurrentUserAdmin() {
        return currentUser().getUserRole().equals(UserRole.ADMIN);
    }
}

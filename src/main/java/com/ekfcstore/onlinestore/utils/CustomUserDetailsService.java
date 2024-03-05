package com.ekfcstore.onlinestore.utils;

import com.ekfcstore.onlinestore.user.entity.User;
import com.ekfcstore.onlinestore.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user data from your data source (e.g., database)
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // Create UserDetails object using user data
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthorities(user.getAuthorities())) // Set user authorities/roles
                .accountExpired(!user.isActive()) // Set account expiration status
                .accountLocked(!user.isActive()) // Set account lock status
                .credentialsExpired(!user.isActive()) // Set credentials expiration status
                .disabled(!user.isActive()) // Set account enabled/disabled status
                .build();

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        List<GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }
}

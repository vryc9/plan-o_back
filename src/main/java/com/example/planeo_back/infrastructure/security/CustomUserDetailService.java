package com.example.planeo_back.infrastructure.security;

import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.infrastructure.adapter.repository.user.JpaUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final JpaUserRepository repository;

    public CustomUserDetailService(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .authorities(Collections.emptyList())
                .build();
    }
}

package com.example.planeo_back.infrastructure.adapter.repository.user;

import com.example.planeo_back.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

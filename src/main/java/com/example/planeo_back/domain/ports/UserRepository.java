package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findById(Long id);

    List<User> findAll();

    User save(User user);

    void delete(User user);

    Optional<User> findUserByUsername(String username);
}

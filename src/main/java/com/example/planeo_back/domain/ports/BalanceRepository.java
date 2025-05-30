package com.example.planeo_back.domain.ports;


import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository {
    Optional<Balance> findById(Long id);

    List<Balance> findAll();

    Balance save(Balance balance);

    void delete(Balance balance);

    Balance findBalanceByUser(User user);
}

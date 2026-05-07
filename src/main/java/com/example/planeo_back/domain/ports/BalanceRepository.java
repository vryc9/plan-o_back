package com.example.planeo_back.domain.ports;


import com.example.planeo_back.domain.entity.Balance;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository {
    Optional<Balance> findById(Long id);

    List<Balance> findAll();

    Balance save(Balance balance);

    void delete(Balance balance);

    Balance findBalanceByUsername(String username);

    void decreaseCurrentBalance(String username, double amount);

    boolean balanceExistForUser(String username);
}

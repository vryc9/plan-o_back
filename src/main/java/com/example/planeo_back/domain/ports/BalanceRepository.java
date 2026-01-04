package com.example.planeo_back.domain.ports;


import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BalanceRepository {
    Optional<Balance> findById(Long id);

    List<Balance> findAll();

    Balance save(Balance balance);

    void delete(Balance balance);

    Balance findBalanceByUser(User user);

    void decreaseCurrentBalance(Long userId, double amount);

}

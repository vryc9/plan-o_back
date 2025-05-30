package com.example.planeo_back.infrastructure.adapter.repository.balance;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBalanceRepository extends JpaRepository<Balance, Long> {
    Balance findBalanceByUser(User user);
}

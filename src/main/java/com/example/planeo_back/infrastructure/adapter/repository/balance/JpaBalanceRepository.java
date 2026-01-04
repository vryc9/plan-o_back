package com.example.planeo_back.infrastructure.adapter.repository.balance;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBalanceRepository extends JpaRepository<Balance, Long> {
    Balance findBalanceByUser(User user);
    @Modifying

    @Query("UPDATE Balance b SET b.currentBalance = b.currentBalance - :amount WHERE b.user.id = :userId")
    void decreaseCurrentBalance(Long userId, double amount);
}

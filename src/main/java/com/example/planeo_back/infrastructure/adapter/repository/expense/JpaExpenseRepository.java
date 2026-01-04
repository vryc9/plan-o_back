package com.example.planeo_back.infrastructure.adapter.repository.expense;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpenseByUser(User user);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.id = :userId AND e.status = :status")
    float sumByUserIdAndStatus(@Param("userId") Long userId, @Param("status") ExpenseStatus status);
}

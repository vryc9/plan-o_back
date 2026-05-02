package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.models.ExpensePerMount;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends IGenericCrudRepository<Expense> {
    List<Expense> findExpenseByUser(User user);
    double sumByUserIdAndStatus(@Param("userId") Long userId, @Param("status") ExpenseStatus status);
    List<ExpensePerMount> getExpensePerMonthByUser(User user);
    Expense update (Expense expense);
}

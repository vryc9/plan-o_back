package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.models.ExpenseByTag;
import com.example.planeo_back.domain.models.ExpensePerMount;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends IGenericCrudRepository<Expense> {
    List<Expense> findExpenseByUsername(String username);
    double sumByUserIdAndStatus(@Param("username") String username, @Param("status") ExpenseStatus status);
    List<ExpensePerMount> getExpensePerMonthByUser(String username);
    Expense update (Expense expense);
    List<ExpenseByTag> getExpenseByTag(String username);
}

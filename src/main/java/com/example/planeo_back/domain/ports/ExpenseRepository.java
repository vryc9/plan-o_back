package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;

import java.util.List;

public interface ExpenseRepository extends IGenericCrudRepository<Expense> {
    List<Expense> findExpenseByUser(User user);
}

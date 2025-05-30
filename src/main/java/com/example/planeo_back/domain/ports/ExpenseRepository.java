package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    Optional<Expense> findById(Long id);

    List<Expense> findAll();

    Expense save(Expense expense);

    void delete(Expense expense);

    List<Expense> findExpenseByUser(User user);
}

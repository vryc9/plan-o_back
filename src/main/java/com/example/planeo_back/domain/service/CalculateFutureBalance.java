package com.example.planeo_back.domain.service;

import com.example.planeo_back.domain.models.balance.BalanceDomain;
import com.example.planeo_back.domain.models.expense.ExpenseDomain;
import com.example.planeo_back.infrastructure.adapter.repository.entity.Balance;
import com.example.planeo_back.infrastructure.adapter.repository.entity.Expense;

import java.util.List;

public class CalculateFutureBalance {

    public static double calculFutureBalance(List<ExpenseDomain> expenses, BalanceDomain balance, Double amount) {

        if (expenses.isEmpty()) {
            return balance.currentBalance() - amount;
        }

        if (balance.futureBalance() == 0) {
            Double totalExpenses = expenses.stream()
                    .map(ExpenseDomain::amount)
                    .reduce(0.0, Double::sum);

            return balance.currentBalance() - totalExpenses;
        }
        return balance.futureBalance() - amount;
    }
}

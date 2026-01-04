package com.example.planeo_back.domain.service;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;

import java.util.List;

public class CalculateFutureBalance {

    public static double calculFutureBalance(List<Expense> expenses, Balance balance, double amount) {

        if (expenses.isEmpty()) {
            return balance.getCurrentBalance() - amount;
        }

        if (balance.getFutureBalance() == 0) {
            int totalExpenses = expenses.stream().map(Expense::getAmount).reduce(0, Integer::sum);
            return balance.getCurrentBalance() - totalExpenses;
        }
        return balance.getFutureBalance() - amount;
    }
}

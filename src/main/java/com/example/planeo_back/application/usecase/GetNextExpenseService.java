package com.example.planeo_back.application.usecase;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetNextExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;

    public GetNextExpenseService(ExpenseRepository expenseRepository, AuthService authService, UserRepository userRepository, BalanceRepository balanceRepository) {
        this.expenseRepository = expenseRepository;
        this.authService = authService;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
    }

    public void deductAmount(long id, String username) {
        int amount = expenseRepository.findById(id).getAmount();
        User user = userRepository.findUserByUsername(username);
        Balance balance = user.getBalance();
        if (balance.getCurrentBalance() != 0) {
            Expense expense = expenseRepository.findById(id);
            balance.setCurrentBalance(balance.getCurrentBalance() - amount);
            if (isLastExpense()) {
                balance.setFutureBalance(0);
            }
            balanceRepository.save(balance);
            expenseRepository.delete(expense);
        }
    }

    private boolean isLastExpense() {
        return expenseRepository.findAll().size() == 1;
    }
}

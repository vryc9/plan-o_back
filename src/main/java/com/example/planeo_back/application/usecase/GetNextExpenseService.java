package com.example.planeo_back.application.usecase;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
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

    public void processExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expense.getStatus() == ExpenseStatus.PROCESSED) {
            return;
        }

        balanceRepository.decreaseCurrentBalance(expense.getUser().getId(), expense.getAmount());

        expense.setStatus(ExpenseStatus.PROCESSED);
        expenseRepository.save(expense);
        updateFutureBalance(expense.getUser());
    }

    private void updateFutureBalance(User user) {
        Balance balance = user.getBalance();
        int pendingExpensesSum = expenseRepository.sumByUserIdAndStatus(user.getId(), ExpenseStatus.PENDING);
        int newFutureBalance = balance.getCurrentBalance() - pendingExpensesSum;
        balance.setFutureBalance(newFutureBalance);
        balanceRepository.save(balance);
    }
}

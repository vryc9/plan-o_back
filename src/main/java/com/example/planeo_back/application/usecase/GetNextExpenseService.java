package com.example.planeo_back.application.usecase;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.infrastructure.sse.EventName;
import com.example.planeo_back.infrastructure.sse.SseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetNextExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BalanceRepository balanceRepository;
    private final SseService sseService;

    public GetNextExpenseService(ExpenseRepository expenseRepository, BalanceRepository balanceRepository, SseService sseService) {
        this.expenseRepository = expenseRepository;
        this.balanceRepository = balanceRepository;
        this.sseService = sseService;
    }

    public void processExpense(Long expenseId, Long userId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expense.getStatus() == ExpenseStatus.PROCESSED) {
            return;
        }
        balanceRepository.decreaseCurrentBalance(expense.getUser().getId(), expense.getAmount());
        expense.setStatus(ExpenseStatus.PROCESSED);
        expenseRepository.save(expense);
        updateFutureBalance(expense.getUser());
        sseService.send(userId, EventName.UPDATED_EXPENSE, "Modification de l'expense: " + expense.getLabel());
    }

    private void updateFutureBalance(User user) {
        Balance balance = user.getBalance();
        double pendingExpensesSum = expenseRepository.sumByUserIdAndStatus(user.getId(), ExpenseStatus.PENDING);
        double newFutureBalance = balance.getCurrentBalance() - pendingExpensesSum;
        balance.setFutureBalance(newFutureBalance);
        balanceRepository.save(balance);
    }
}

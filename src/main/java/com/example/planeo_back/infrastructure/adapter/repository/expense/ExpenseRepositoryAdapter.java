package com.example.planeo_back.infrastructure.adapter.repository.expense;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.models.ExpenseByTag;
import com.example.planeo_back.domain.models.ExpensePerMount;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {
    private final JpaExpenseRepository repository;
    private final AuthService authService;

    public ExpenseRepositoryAdapter(JpaExpenseRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElse(null));
    }

    @Override
    public List<Expense> findAll() {
        return repository.findAll();
    }

    @Override
    public Expense save(Expense expense) {
        return repository.save(expense);
    }

    public Expense update(Expense expense) {
        String username = authService.getUsername();
        expense.setUsername(username);
        return repository.save(expense);
    }

    @Override
    public void delete(Expense expense) {
        repository.delete(expense);
    }

    @Override
    public List<Expense> findExpenseByUsername(String username) {
        return repository.findExpenseByUsername(username);
    }

    @Override
    public double sumByUserIdAndStatus(String username, ExpenseStatus status) {
        return repository.sumByUserIdAndStatus(username, status);
    }

    @Override
    public List<ExpensePerMount> getExpensePerMonthByUser(String username) {
        return repository.getExpensePerMountByUser(username);
    }

    @Override
    public List<ExpenseByTag> getExpenseByTag(String username) {
        return repository.findTotalAmountByTagForCurrentMonth(username);
    }
}

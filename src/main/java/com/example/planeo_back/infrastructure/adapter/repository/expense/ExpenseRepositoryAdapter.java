package com.example.planeo_back.infrastructure.adapter.repository.expense;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {
    private final JpaExpenseRepository repository;

    public ExpenseRepositoryAdapter(JpaExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expense findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Expense> findAll() {
        return repository.findAll();
    }

    @Override
    public Expense save(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public void delete(Expense expense) {
        repository.delete(expense);
    }

    @Override
    public List<Expense> findExpenseByUser(User user) {
        return repository.findExpenseByUser(user);
    }
}

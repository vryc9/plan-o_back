package com.example.planeo_back.infrastructure.adapter.repository.expense;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.models.ExpensePerMount;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {
    private final JpaExpenseRepository repository;
    private final UserRepository userRepository;

    public ExpenseRepositoryAdapter(JpaExpenseRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(username);
        expense.setUser(user);
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

    @Override
    public double sumByUserIdAndStatus(Long userId, ExpenseStatus status) {
        return repository.sumByUserIdAndStatus(userId, status);
    }

    @Override
    public List<ExpensePerMount> getExpensePerMonthByUser(User user) {
        return repository.getExpensePerMountByUser(user);
    }
}

package com.example.planeo_back.infrastructure.adapter.repository.balance;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.ports.BalanceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BalanceRepositoryAdapter implements BalanceRepository {
    private final JpaBalanceRepository repository;

    public BalanceRepositoryAdapter(JpaBalanceRepository jpaBalanceRepository) {
        this.repository = jpaBalanceRepository;
    }

    @Override
    public Optional<Balance> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Balance> findAll() {
        return repository.findAll();
    }

    @Override
    public Balance save(Balance balance) {
        return repository.save(balance);
    }

    @Override
    public void delete(Balance balance) {
        repository.delete(balance);
    }

    @Override
    public Balance findBalanceByUser(User user) {
        return repository.findBalanceByUser(user);
    }

    @Override
    public void decreaseCurrentBalance(Long userId, double amount) {
        repository.decreaseCurrentBalance(userId, amount);
    }
}

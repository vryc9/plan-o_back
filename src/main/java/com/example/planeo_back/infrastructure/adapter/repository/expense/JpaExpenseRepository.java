package com.example.planeo_back.infrastructure.adapter.repository.expense;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpenseByUser(User user);
}

package com.example.planeo_back.application.service;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseMapperDTO mapper;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository repository, ExpenseMapperDTO mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public ExpenseDTO getExpenseById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public List<ExpenseDTO> getAllExpenses() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ExpenseDTO createExpense(ExpenseDTO dto) {
        User user = userRepository.findUserByUsername(dto.getUsername());
        Expense expense = mapper.toEntity(dto);
        expense.setUser(user);
        return mapper.toDTO(repository.save(expense));
    }

    public void deleteExpense(ExpenseDTO expenseDTO) {
        Expense expense = mapper.toEntity(expenseDTO);
        repository.delete(expense);
    }
}

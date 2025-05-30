package com.example.planeo_back.application.service;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.infrastructure.mapper.UserMapperDTO;
import com.example.planeo_back.infrastructure.mapper.UserMapperDTO;
import com.example.planeo_back.web.DTO.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repository;
    private final ExpenseRepository expenseRepository;
    private final UserMapperDTO mapper;
    private final ExpenseMapperDTO IExpenseMapper;

    public UserService(UserRepository repository, UserMapperDTO mapper, ExpenseRepository expenseRepository, ExpenseMapperDTO IExpenseMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
        this.IExpenseMapper = IExpenseMapper;
    }

    public UserDTO getUserById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public List<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public UserDTO createUser(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(user));
    }

    public void deleteUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        repository.delete(user);
    }

    public UserDTO getUser(String username) {
        User user = repository.findUserByUsername(username);
        List<Expense> expense = expenseRepository.findExpenseByUser(user);
        user.setExpenses(expense);
        return mapper.toDTO(user);
    }
}

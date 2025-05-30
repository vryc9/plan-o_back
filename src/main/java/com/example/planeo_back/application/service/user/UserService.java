package com.example.planeo_back.application.service.user;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.infrastructure.mapper.UserMapperDTO;
import com.example.planeo_back.web.DTO.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final ExpenseRepository expenseRepository;
    private final UserMapperDTO mapper;
    private final ExpenseMapperDTO IExpenseMapper;
    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    public UserService(UserRepository repository, UserMapperDTO mapper, ExpenseRepository expenseRepository, ExpenseMapperDTO IExpenseMapper, BalanceRepository balanceRepository, BalanceMapper balanceMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
        this.IExpenseMapper = IExpenseMapper;
        this.balanceRepository = balanceRepository;
        this.balanceMapper = balanceMapper;
    }

    public UserDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id));
    }

    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public UserDTO save(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(user));
    }

    public void delete(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        repository.delete(user);
    }

    public UserDTO getUser(String username) {
        User user = repository.findUserByUsername(username);

        List<Expense> expense = expenseRepository.findExpenseByUser(user);
        List<ExpenseDTO> expenseDTOS = IExpenseMapper.toDTO(expense);

        Balance balance = balanceRepository.findBalanceByUser(user);
        BalanceDTO balanceDTO = balanceMapper.toDTO(balance);

        UserDTO userDTO = mapper.toDTO(user);
        userDTO.setBalance(balanceDTO);
        userDTO.setExpenses(expenseDTOS);

        return mapper.toDTO(user);
    }
}

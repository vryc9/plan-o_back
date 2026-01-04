package com.example.planeo_back.application.service.balance;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.domain.service.Guard;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.web.DTO.BalanceDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BalanceService implements IBalanceService {

    private final BalanceRepository repository;
    private final BalanceMapper mapper;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapperDTO IExpenseMapper;

    public BalanceService(BalanceRepository balanceRepository, BalanceMapper balanceMapper, UserRepository userRepository, ExpenseRepository expenseRepository, ExpenseMapperDTO IExpenseMapper) {
        this.repository = balanceRepository;
        this.mapper = balanceMapper;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.IExpenseMapper = IExpenseMapper;
    }

    @Override
    public BalanceDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public List<BalanceDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BalanceDTO save(BalanceDTO balanceDTO) throws IllegalAccessException {
        Guard.checkIfObjectIsNull(balanceDTO);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(username);
        Balance balance = mapper.toEntity(balanceDTO);
        balance.setUser(user);
        return mapper.toDTO(repository.save(balance));
    }

    @Override
    public void delete(BalanceDTO balanceDTO) {
        Balance balance = mapper.toEntity(balanceDTO);
        repository.delete(balance);
    }

    @Override
    public BalanceDTO getBalance(String username) {
        User user = userRepository.findUserByUsername(username);
        List<Expense> expense = expenseRepository.findExpenseByUser(user);
        List<ExpenseDTO> expenseDTOS = IExpenseMapper.toDTO(expense);

        Balance balance = repository.findBalanceByUser(user);
        BalanceDTO balanceDTO = mapper.toDTO(balance);

        double pending = expenseDTOS.isEmpty() ? 0.00 : balance.getCurrentBalance() - balance.getFutureBalance();
        balanceDTO.setPendingExpenses(Math.max(0, pending));

        return balanceDTO;
    }

    @Override
    public BalanceDTO update() {
        return null;
    }
}

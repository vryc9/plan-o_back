package com.example.planeo_back.application.service.balance;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.service.Guard;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.web.DTO.BalanceDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BalanceService implements IBalanceService {

    private final BalanceRepository repository;
    private final BalanceMapper mapper;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapperDTO IExpenseMapper;
    private final AuthService authService;

    public BalanceService(BalanceRepository balanceRepository, BalanceMapper balanceMapper, ExpenseRepository expenseRepository, ExpenseMapperDTO IExpenseMapper, AuthService authService) {
        this.repository = balanceRepository;
        this.mapper = balanceMapper;
        this.expenseRepository = expenseRepository;
        this.IExpenseMapper = IExpenseMapper;
        this.authService  = authService;
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
        String username = authService.getUsername();
        Balance balance = mapper.toEntity(balanceDTO);
        balance.setUsername(username);
        return mapper.toDTO(repository.save(balance));
    }

    @Override
    public void delete(BalanceDTO balanceDTO) {
        Balance balance = mapper.toEntity(balanceDTO);
        repository.delete(balance);
    }

    @Override
    public BalanceDTO getBalance(String username) {
        List<Expense> expense = expenseRepository.findExpenseByUsername(username);
        List<ExpenseDTO> expenseDTOS = IExpenseMapper.toDTO(expense);

        Balance balance = repository.findBalanceByUsername(username);
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

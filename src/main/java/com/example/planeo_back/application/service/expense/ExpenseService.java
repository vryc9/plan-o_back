package com.example.planeo_back.application.service.expense;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.scheduler.SchedulerService;
import com.example.planeo_back.domain.service.CalculateFutureBalance;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseMapperDTO mapper;
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;
    private final AuthService authService;
    private final SchedulerService scheduler;

    public ExpenseService(ExpenseRepository repository, ExpenseMapperDTO mapper, UserRepository userRepository, BalanceRepository balanceRepository, BalanceMapper balanceMapper, AuthService authService, SchedulerService scheduler) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.balanceMapper = balanceMapper;
        this.authService = authService;
        this.scheduler = scheduler;
    }

    public ExpenseDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id));
    }

    public List<ExpenseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ExpenseDTO save(ExpenseDTO dto) throws SchedulerException {
        String username = authService.getUsername();
        User user = userRepository.findUserByUsername(username);

        Balance balance = user.getBalance();
        balance.setFutureBalance(CalculateFutureBalance.calculFutureBalance(user.getExpenses(), balance, dto.getAmount()));
        balanceRepository.save(balance);

        Expense expense = mapper.toEntity(dto);
        expense.setUser(user);

        Expense saved = repository.save(expense);
        scheduler.sheduleJobs(saved);

        return mapper.toDTO(repository.save(saved));
    }

    public void delete(ExpenseDTO expenseDTO) {
        Expense expense = mapper.toEntity(expenseDTO);
        repository.delete(expense);
    }
}

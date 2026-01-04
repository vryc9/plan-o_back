package com.example.planeo_back.application.service.expense;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.ExpenseRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.scheduler.SchedulerService;
import com.example.planeo_back.domain.service.CalculateFutureBalance;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.infrastructure.mapper.ExpenseMapperDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import jakarta.transaction.Transactional;
import org.quartz.SchedulerException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExpenseService implements IExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseMapperDTO mapper;
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final AuthService authService;
    private final SchedulerService scheduler;

    public ExpenseService(ExpenseRepository repository, ExpenseMapperDTO mapper, UserRepository userRepository, BalanceRepository balanceRepository, BalanceMapper balanceMapper, AuthService authService, SchedulerService scheduler) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.authService = authService;
        this.scheduler = scheduler;
    }

    public ExpenseDTO findById(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public List<ExpenseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public ExpenseDTO save(ExpenseDTO dto) throws SchedulerException {
        User user = userRepository.findUserByUsername(authService.getUsername());
        Expense expense = mapper.toEntity(dto);
        expense.setUser(user);
        expense.setStatus(ExpenseStatus.PENDING);

        Balance balance = user.getBalance();
        balance.setFutureBalance(CalculateFutureBalance.calculFutureBalance(user.getExpenses(), balance, dto.getAmount()));
        balanceRepository.save(balance);

        Expense savedExpense = repository.save(expense);
//        scheduler.sheduleJobs(savedExpense);
        return mapper.toDTO(savedExpense);
    }

    public void delete(ExpenseDTO expenseDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(username);
        Expense expense = mapper.toEntity(expenseDTO);
        Balance balance = balanceRepository.findBalanceByUser(user);
        balance.setFutureBalance(balance.getFutureBalance() + expense.getAmount());
        balanceRepository.save(balance);
        repository.delete(expense);
    }
}

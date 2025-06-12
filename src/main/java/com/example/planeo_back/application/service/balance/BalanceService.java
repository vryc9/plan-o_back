package com.example.planeo_back.application.service.balance;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.BalanceRepository;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.infrastructure.mapper.BalanceMapper;
import com.example.planeo_back.web.DTO.BalanceDTO;
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

    public BalanceService(BalanceRepository balanceRepository, BalanceMapper balanceMapper, UserRepository userRepository) {
        this.repository = balanceRepository;
        this.mapper = balanceMapper;
        this.userRepository = userRepository;
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
    public BalanceDTO save(BalanceDTO balanceDTO) {
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
    public BalanceDTO update() {
        return null;
    }
}

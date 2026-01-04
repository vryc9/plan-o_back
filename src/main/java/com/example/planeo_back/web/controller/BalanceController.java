package com.example.planeo_back.web.controller;

import com.example.planeo_back.application.service.balance.BalanceService;
import com.example.planeo_back.web.DTO.BalanceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService service;

    public BalanceController(BalanceService balanceService) {
        this.service = balanceService;
    }

    @GetMapping
    public ResponseEntity<BalanceDTO> getBalance() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.getBalance(username));
    }

    @GetMapping("{id}")
    public ResponseEntity<BalanceDTO> getBalanceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BalanceDTO> createBalance(@RequestBody BalanceDTO balanceDTO) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(balanceDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBalance(@RequestBody BalanceDTO balanceDTO) {
        service.delete(balanceDTO);
        return ResponseEntity.ok().build();
    }
}

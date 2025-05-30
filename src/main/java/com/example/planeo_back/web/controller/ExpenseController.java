package com.example.planeo_back.web.controller;

import com.example.planeo_back.application.service.ExpenseService;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expense")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpense(@PathVariable Long id) {
        return ResponseEntity.ok(service.getExpenseById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllExpenses());
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> create(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createExpense(expenseDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody ExpenseDTO expenseDTO) {
        service.deleteExpense(expenseDTO);
        return ResponseEntity.ok().build();
    }
}

package com.example.planeo_back.web.DTO;

public record BalanceResponseDTO(
     Long id,
     Double currentBalance,
     Double futureBalance,
     Double pendingExpense
    ) {}


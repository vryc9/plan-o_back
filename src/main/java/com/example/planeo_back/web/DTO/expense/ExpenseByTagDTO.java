package com.example.planeo_back.web.DTO.expense;

import com.example.planeo_back.domain.enums.Tag;

public record ExpenseByTagDTO(Tag tag, Double total) {
}

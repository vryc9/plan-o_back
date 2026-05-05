package com.example.planeo_back.web.DTO.expense;

import com.example.planeo_back.domain.entity.enums.Tag;

public record ExpenseByTagDTO(Tag tag, long total) {
}

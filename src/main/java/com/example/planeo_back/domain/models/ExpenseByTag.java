package com.example.planeo_back.domain.models;

import com.example.planeo_back.domain.enums.Tag;

public record ExpenseByTag(Tag tag, Double total) {
}

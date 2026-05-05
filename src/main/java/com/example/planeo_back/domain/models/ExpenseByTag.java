package com.example.planeo_back.domain.models;

import com.example.planeo_back.domain.entity.enums.Tag;

public record ExpenseByTag(Tag tag, long total) {
}

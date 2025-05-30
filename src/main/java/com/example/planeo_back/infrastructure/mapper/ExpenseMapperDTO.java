package com.example.planeo_back.infrastructure.mapper;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapperDTO {
    ExpenseDTO toDTO(Expense expense);

    Expense toEntity(ExpenseDTO expenseDTO);
}

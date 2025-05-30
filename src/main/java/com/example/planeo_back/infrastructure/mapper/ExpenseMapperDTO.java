package com.example.planeo_back.infrastructure.mapper;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapperDTO {
    ExpenseDTO toDTO(Expense expense);

    List<ExpenseDTO> toDTO(List<Expense> expenses);

    Expense toEntity(ExpenseDTO expenseDTO);

    List<Expense> toEntity(List<ExpenseDTO> expenseDTOs);
}

package com.example.planeo_back.infrastructure.mapper;

import com.example.planeo_back.domain.models.expense.ExpenseDomain;
import com.example.planeo_back.infrastructure.adapter.repository.entity.Expense;
import com.example.planeo_back.domain.models.ExpenseByTag;
import com.example.planeo_back.domain.models.ExpensePerMount;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import com.example.planeo_back.web.DTO.expense.ExpenseByTagDTO;
import com.example.planeo_back.web.DTO.expense.ExpensePerMonthDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDomain fromEntityToDomain(Expense expense);
    ExpenseDomain fromDtoToDomain(ExpenseDTO expenseDTO);
    ExpenseDTO fromDomainToDTO(ExpenseDomain domain);
    ExpenseDTO toDTO(Expense expense);
    List<ExpenseDTO> toDTO(List<Expense> expenses);
    List<ExpensePerMonthDTO> transformExpensePerMonthDTO(List<ExpensePerMount> expensePerMounts);
    List<ExpenseByTagDTO> transformExpenseByTags(List<ExpenseByTag> expenseByTags);
    Expense toEntity(ExpenseDomain expenseDomain);
    List<Expense> toEntity(List<ExpenseDTO> expenseDTOs);
}

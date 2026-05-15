package com.example.planeo_back.application.service.expense;

import com.example.planeo_back.application.service.IGenericService;
import com.example.planeo_back.web.DTO.ExpenseDTO;
import com.example.planeo_back.web.DTO.expense.ExpenseByTagDTO;
import com.example.planeo_back.web.DTO.expense.ExpensePerMonthDTO;

import java.util.List;

public interface IExpenseService extends IGenericService<ExpenseDTO, ExpenseDTO> {
    List<ExpensePerMonthDTO> getExpensePerMonths();
    List<ExpenseByTagDTO> getExpenseByTags();
}

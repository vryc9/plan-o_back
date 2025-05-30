package com.example.planeo_back.web.DTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String username;
    private List<ExpenseDTO> expenses = new ArrayList<ExpenseDTO>();


    public UserDTO() {
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }
}

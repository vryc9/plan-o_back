package com.example.planeo_back.web.DTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String username;
    private List<ExpenseDTO> expenses = new ArrayList<ExpenseDTO>();
    private BalanceDTO balance;


    public UserDTO() {
    }

    public UserDTO(String username, BalanceDTO balance) {
        this.username = username;
        this.balance = balance;
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

    public BalanceDTO getBalance() {
        return balance;
    }

    public void setBalance(BalanceDTO balance) {
        this.balance = balance;
    }
}

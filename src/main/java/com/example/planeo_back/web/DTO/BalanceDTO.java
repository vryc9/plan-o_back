package com.example.planeo_back.web.DTO;

import java.util.Optional;

public class BalanceDTO {
    private int currentBalance;
    private int futureBalance;
    private Double pendingExpenses;

    public BalanceDTO() {
    }

    public BalanceDTO(int currentBalance, int futureBalance, Double pendingExpenses) {
        this.currentBalance = currentBalance;
        this.futureBalance = futureBalance;
        this.pendingExpenses = pendingExpenses;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getFutureBalance() {
        return this.futureBalance;
    }

    public void setFutureBalance(int futureBalance) {
        this.futureBalance = futureBalance;
    }

    public Double getPendingExpenses() {
        return pendingExpenses;
    }

    public void setPendingExpenses(Double pendingExpenses) {
        this.pendingExpenses = pendingExpenses;
    }
}

package com.example.planeo_back.web.DTO;

import java.util.Optional;

public class BalanceDTO {
    private double currentBalance;
    private double futureBalance;
    private double pendingExpenses;

    public BalanceDTO() {
    }

    public BalanceDTO(double currentBalance, double futureBalance, Double pendingExpenses) {
        this.currentBalance = currentBalance;
        this.futureBalance = futureBalance;
        this.pendingExpenses = pendingExpenses;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getFutureBalance() {
        return this.futureBalance;
    }

    public void setFutureBalance(double futureBalance) {
        this.futureBalance = futureBalance;
    }

    public Double getPendingExpenses() {
        return pendingExpenses;
    }

    public void setPendingExpenses(Double pendingExpenses) {
        this.pendingExpenses = pendingExpenses;
    }
}

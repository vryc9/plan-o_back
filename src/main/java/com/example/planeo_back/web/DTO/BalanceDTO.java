package com.example.planeo_back.web.DTO;

import java.util.Optional;

public class BalanceDTO {
    private int currentBalance;
    private int futureBalance;

    public BalanceDTO() {
    }

    public BalanceDTO(int currentBalance, int futureBalance) {
        this.currentBalance = currentBalance;
        this.futureBalance = futureBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getFutureBalance() {
        return futureBalance;
    }

    public void setFutureBalance(int futureBalance) {
        this.futureBalance = futureBalance;
    }
}

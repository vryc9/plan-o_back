package com.example.planeo_back.web.DTO;

public class BalanceDTO {
    private int currentBalance;
    private int futureBalance;
    private Long idUser;

    public BalanceDTO() {
    }

    public BalanceDTO(int currentBalance, int futureBalance, Long idUser) {
        this.currentBalance = currentBalance;
        this.futureBalance = futureBalance;
        this.idUser = idUser;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}

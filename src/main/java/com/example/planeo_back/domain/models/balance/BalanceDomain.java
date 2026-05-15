package com.example.planeo_back.domain.models.balance;

public record BalanceDomain (
        Long id,
        String username,
        Double currentBalance,
        Double futureBalance,
        Double pendingExpense
        ){

    public Double pendingExpenses(Double futureEngagements) {
        double pending = currentBalance - futureEngagements;
        return Math.max(0, pending);
    }

    public BalanceDomain withFutureBalance(Double pendingSum) {
        return new BalanceDomain(id,username, currentBalance, currentBalance - pendingSum, pendingExpense);
    }

}

package com.example.planeo_back.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float currentBalance;

    @Column(nullable = true)
    private double futureBalance;

    @Column(nullable = false)
    private String username;

    public Balance() {
    }

    public Balance(int currentBalance, int futureBalance, String username) {
        this.currentBalance = currentBalance;
        this.futureBalance = futureBalance;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
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

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

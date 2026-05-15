package com.example.planeo_back.infrastructure.adapter.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double currentBalance;

    @Column(nullable = true)
    private Double futureBalance;

    @Column(nullable = false)
    private String username;

    public Balance() {
    }

    public Balance(Double currentBalance, Double futureBalance, String username) {
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

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getFutureBalance() {
        return this.futureBalance;
    }

    public void setFutureBalance(double futureBalance) {
        this.futureBalance = futureBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Balance() {
    }

    public Balance(int currentBalance) {
        this.currentBalance = currentBalance;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

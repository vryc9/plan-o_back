package com.example.planeo_back.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int currentBalance;

    @Column(nullable = true)
    private int futureBalance;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

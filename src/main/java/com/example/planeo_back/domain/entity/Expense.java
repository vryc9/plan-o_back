package com.example.planeo_back.domain.entity;

import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.entity.enums.Tag;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private Tag tag;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseStatus status;

    private Date date;
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Expense() {
    }

    public Expense(String label, Date date, Tag tag, int amount, ExpenseStatus status) {
        this.label = label;
        this.date = date;
        this.tag = tag;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public User getUser() {
        return user;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

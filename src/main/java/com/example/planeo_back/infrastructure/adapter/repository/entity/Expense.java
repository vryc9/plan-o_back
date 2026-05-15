package com.example.planeo_back.infrastructure.adapter.repository.entity;

import com.example.planeo_back.domain.enums.ExpenseStatus;
import com.example.planeo_back.domain.enums.Tag;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.ORDINAL)
    private Tag tag;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseStatus status;

    private Date date;
    private String label;

    @Column(nullable = false)
    private String username;

    private boolean recurring;


    public Expense() {
    }

    public Expense(String label, Date date, Tag tag, Double amount, String username, boolean recurring) {
        this.label = label;
        this.date = date;
        this.tag = tag;
        this.amount = amount;
        this.status = ExpenseStatus.PENDING;
        this.username = username;
        this.recurring = recurring;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

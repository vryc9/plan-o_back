package com.example.planeo_back.web.DTO;

import com.example.planeo_back.domain.entity.enums.ExpenseStatus;
import com.example.planeo_back.domain.entity.enums.Tag;

import java.util.Date;

public class ExpenseDTO {
    private Long id;
    private int amount;
    private Tag tag;
    private ExpenseStatus status;
    private Date date;
    private String label;
    private boolean recurring;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, Tag tag, int amount, Date date, String label, ExpenseStatus status, boolean recurring) {
        this.id = id;
        this.tag = tag;
        this.amount = amount;
        this.date = date;
        this.label = label;
        this.status = status;
        this.recurring = recurring;
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
}

package com.example.planeo_back.web.DTO;

import com.example.planeo_back.domain.entity.enums.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseDTO {
    private int amount;
    private Tag tag;
    private Date date;
    private String label;
    private String username;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Tag tag, int amount, Date date, String label, String username) {
        this.tag = tag;
        this.amount = amount;
        this.date = date;
        this.label = label;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

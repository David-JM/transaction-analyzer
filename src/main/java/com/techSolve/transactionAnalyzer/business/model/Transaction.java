package com.techSolve.transactionAnalyzer.business.model;

import java.time.LocalDate;

public class Transaction {

    private LocalDate date;
    private Integer value;
    private String category;

    public Transaction(LocalDate date, Integer value, String category) {
        this.date = date;
        this.value = value;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

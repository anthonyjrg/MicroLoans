package com.nassaulabs.microloans;

import java.util.Date;

public class Debt {
    public int id;
    private int debtorId;
    private float amount;
    private float interestRate;
    private boolean active;
    private Date date;

    public Debt(int debtorId, float amount, float interestRate){
        this.debtorId = debtorId;
        this.amount = amount;
        this.interestRate = interestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(int debtorId) {
        this.debtorId = debtorId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

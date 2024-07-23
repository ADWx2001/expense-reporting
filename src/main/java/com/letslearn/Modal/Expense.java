package com.letslearn.Modal;

public class Expense {
    private int id;
    private String date;
    private double amount;
    private String machine;
    private String reason;
    private String action;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getAction() {
    	return action;
    }
    public void setAction(String action) {
    	this.action = action;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }
    public void setReason(String reason) {
    	this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
}

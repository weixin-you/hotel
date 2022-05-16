package com.weixin.hotelapp;

public class Payment {
    private String month;
    private double amount;

    public Payment(String monthIn, double amountIn ){
        month = monthIn;
        amount = amountIn;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

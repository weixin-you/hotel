package com.weixin.hotelapp;

public class Tenant {
    private String name;
    private PaymentList payments;
    private int roomNumber;
    public static final int MAX = 12;

    public Tenant(String nameIn, int roomNumberIn){
        name = nameIn;
        roomNumber = roomNumberIn;
        payments = new PaymentList(MAX);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentList getPayments() {
        return payments;
    }

    public void setPayments(PaymentList payments) {
        this.payments = payments;
    }

}


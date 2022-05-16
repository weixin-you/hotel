package com.weixin.hotelapp;

import java.util.ArrayList;

public class PaymentList {


    private ArrayList<Payment> paymentList;
    public final int MAX;

    public PaymentList(int maxIn){
        paymentList = new ArrayList<>();
        MAX = maxIn;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public boolean addPayment(Payment payment){
        if(!isFull()){
            paymentList.add(payment);
            return true;
        }
        return false;
    }
    public boolean removePayment(String monthIn){
        Payment searched = searchPayment(monthIn);
        if(searched != null){
            paymentList.remove(searched);
            return true;
        }
        return false;
    }

    public Payment searchPayment(String monthIn){
        for (Payment payment: paymentList) {
            if(payment.getMonth()==monthIn){
                return payment;
            }
        }
        return null;
    }
    public boolean isFull(){
        return paymentList.size() == MAX;
    }

    public boolean isEmpty(){
        return paymentList.isEmpty();
    }

    @Override
    public String toString(){
        return paymentList.toString();
    }
}

package com.model;

import java.time.LocalDate;

public class Billing {
    private static int billIdCounter = 0; 

    private int billId;
    private Member member;
    private Package pack;
    private LocalDate billingDate;
    private double amount;
    private String billingStatus;

    
    public Billing() {
        this.billId = ++billIdCounter;
    }

    
    public Billing(Member member, Package pack, LocalDate billingDate, double amount, String billingStatus) {
        this.billId = ++billIdCounter;
        this.member = member;
        this.pack = pack;
        this.billingDate = billingDate;
        this.amount = amount;
        this.billingStatus = billingStatus;
    }

    

    public int getBillId() {
        return billId;
    }

   public void setBillId(int billId) {
	   this.billId=billId;
   }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    @Override
    public String toString() {
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        return "\n" +
            CYAN + "《 Billing Details 》" + RESET + "\n" +
            "────────────────────────────────────────────\n" +
            GREEN + "Bill ID       " + RESET + ": " + billId + "\n" +
            GREEN + "Member Name   " + RESET + ": " + (member != null ? member.getName() : "N/A") + "\n" +
            GREEN + "Package Name  " + RESET + ": " + (pack != null ? pack.getName() : "N/A") + "\n" +
            GREEN + "Billing Date  " + RESET + ": " + (billingDate != null ? billingDate : "N/A") + "\n" +
            GREEN + "Amount        " + RESET + ": ₹" + String.format("%.2f", amount) + "\n" +
            GREEN + "Status        " + RESET + ": " + billingStatus + "\n" +
            "────────────────────────────────────────────";
    }

}

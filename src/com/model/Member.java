package com.model;

import java.time.LocalDate;

public class Member {
    private static int idCounter = 0; // Used to auto-increment memberId
    private int memberId;
    private String username;
    private String name;
    private long contact;
    private String email;
    private String address;
    private String password;
    private LocalDate dateOfJoined; 
    private boolean isActive;

    public Member() {}

    public Member(String username, String name, long contact, String email, String address, String password) {
        this.memberId = ++idCounter; 
        this.username = username;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
        this.dateOfJoined = LocalDate.now(); 
        this.isActive = true;
    }

    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
    	this.memberId=memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfJoined() {
        return dateOfJoined;
    }

    public void setDateOfJoined(LocalDate dateOfJoined) {
        this.dateOfJoined = dateOfJoined;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RESET = "\u001B[0m";

        return "\n" +
            CYAN + "《 Member Profile 》" + RESET + "\n" +
            "────────────────────────────────────────────\n" +
            GREEN + "Member ID     " + RESET + ": " + memberId + "\n" +
            GREEN + "Username      " + RESET + ": " + username + "\n" +
            GREEN + "Name          " + RESET + ": " + name + "\n" +
            GREEN + "Contact       " + RESET + ": " + contact + "\n" +
            GREEN + "Email         " + RESET + ": " + email + "\n" +
            GREEN + "Address       " + RESET + ": " + address + "\n" +
            GREEN + "Password      " + RESET + ": " + password.replaceAll(".", "*") + "\n" +
            GREEN + "Date Joined   " + RESET + ": " + dateOfJoined + "\n" +
            GREEN + "Status        " + RESET + ": " + (isActive ? YELLOW + "Active" + RESET : "Inactive") + "\n" +
            "────────────────────────────────────────────";
    }
}

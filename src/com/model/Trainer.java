package com.model;

public class Trainer {
    private static int idCounter = 0; // Static counter for IDs
    private int trainerId;            // Instance variable for unique ID
    private String userName;
    private String name;
    private String specialization;
    private long contact;
    private String email;
    private boolean isActive;
    private String password;

    public Trainer() {}

    public Trainer(String userName, String name, String specialization, long contact, String email, boolean isActive, String password) {
        this.trainerId = ++idCounter; // Assign unique ID
        this.userName = userName;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.email = email;
        this.isActive = isActive;
        this.password = password;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
    	this.trainerId=trainerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public boolean getIsActive() {
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

    @Override
    public String toString() {
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RESET = "\u001B[0m";

        StringBuilder sb = new StringBuilder();
        sb.append("\n")
          .append(CYAN).append("《 Trainer Profile 》").append(RESET).append("\n")
          .append("────────────────────────────────────────────\n")
          .append(GREEN).append("Trainer ID     ").append(RESET).append(": ").append(trainerId).append("\n")
          .append(GREEN).append("Username       ").append(RESET).append(": ").append(userName).append("\n")
          .append(GREEN).append("Name           ").append(RESET).append(": ").append(name).append("\n")
          .append(GREEN).append("Specialization ").append(RESET).append(": ").append(specialization).append("\n")
          .append(GREEN).append("Contact        ").append(RESET).append(": ").append(contact).append("\n")
          .append(GREEN).append("Email          ").append(RESET).append(": ").append(email).append("\n")
          .append(GREEN).append("Status         ").append(RESET).append(": ").append(isActive ? YELLOW + "Active" + RESET : "Inactive").append("\n")
          .append(GREEN).append("Password       ").append(RESET).append(": ").append(password.replaceAll(".", "*")).append("\n")
          .append("────────────────────────────────────────────");

        return sb.toString();
    }
}
